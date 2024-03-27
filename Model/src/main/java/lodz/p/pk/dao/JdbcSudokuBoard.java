package lodz.p.pk.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import lodz.p.pk.exceptions.ReadDaoException;
import lodz.p.pk.exceptions.SqldatabaseException;
import lodz.p.pk.exceptions.WriteDaoException;
import lodz.p.pk.sudoku.BacktrackingSudokuSolver;
import lodz.p.pk.sudoku.SudokuBoard;
import lodz.p.pk.sudoku.SudokuSolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class JdbcSudokuBoard implements Dao<SudokuBoard>, AutoCloseable {

    private static Logger logger = LoggerFactory.getLogger(FileSudokuBoardDao.class);

    ResourceBundle bundle = ResourceBundle.getBundle("LangBundle");

    private final String jdbcUrl = "jdbc:sqlite:SudokuDatabase.db";

    public JdbcSudokuBoard(String boardName) {
        this.boardName = boardName;
    }

    private String boardName;

    private Connection conn = null;

    private void executeSQl(String sqlQuery, Connection conn) throws SqldatabaseException {
        try (PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery)) {

           preparedStatement.execute();
           conn.commit();
        } catch (SQLException e) {
            logger.info(bundle.getString("SqldatabaseException"));
            throw new SqldatabaseException(e);
        }
    }


    @Override
    public SudokuBoard read() throws ReadDaoException, SqldatabaseException {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(jdbcUrl);
                conn.setAutoCommit(false);
            } catch (SQLException e) {
                logger.info(bundle.getString("SqldatabaseException"));
                throw new SqldatabaseException(e);
            }
        }


        String sqlValues = "SELECT value FROM SudokuFields" + boardName;

        try (Statement statement1 = conn.createStatement();
          ResultSet rs = statement1.executeQuery(sqlValues)) {


            while (rs.next()) {
                board.setField((rs.getRow() - 1) / 9,
                        (rs.getRow() - 1)  % 9, rs.getInt("value"));
            }
            conn.commit();
        } catch (SQLException e) {
            logger.info(bundle.getString("ReadException"));
            throw new ReadDaoException(e);
        }
        return board;
    }

    @Override
    public void write(SudokuBoard obj) throws WriteDaoException, SqldatabaseException {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(jdbcUrl);
                conn.setAutoCommit(false);
            } catch (SQLException e) {
                logger.info(bundle.getString("SqldatabaseException"));
                throw new SqldatabaseException(e);
            }
        }
        String sqlBoardTable = "CREATE TABLE IF NOT EXISTS boards "
                + "(boardID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + "boardName TEXT, UNIQUE(boardName))";

        String sqlBoardTableInsert = "INSERT OR IGNORE INTO boards (boardName) values (?)";

        String sqlDropFields = "DROP TABLE IF EXISTS SudokuFields" + boardName;
        String sqlFieldsTable =  "CREATE TABLE IF NOT EXISTS " + "SudokuFields" + boardName
                + "(value INTEGER, position INTEGER, boardID INTEGER, "
                + "FOREIGN KEY(boardID) REFERENCES boards(boardID))";

        String sqlFieldsInsert = "INSERT INTO SudokuFields"
                + boardName + "(value, position, boardID) values (?,?,?)";
        String selectID = "SELECT boardID FROM boards ORDER BY boardID DESC LIMIT 1";

        executeSQl(sqlBoardTable, conn);
        executeSQl(sqlDropFields, conn);
        executeSQl(sqlFieldsTable, conn);

        try (PreparedStatement preparedStatement2 = conn.prepareStatement(sqlBoardTableInsert)) {
            preparedStatement2.setString(1, boardName);
            preparedStatement2.executeUpdate();
        } catch (SQLException e) {
            logger.info(bundle.getString("WriteException"));
            throw new WriteDaoException(e);
        }

        try (
             PreparedStatement preparedStatement4 = conn.prepareStatement(sqlFieldsInsert);
             ResultSet rs = conn.createStatement().executeQuery(selectID);
        ) {


            for (int i = 0; i < 81; i++) {
                preparedStatement4.setInt(1, obj.getField(i / 9,i % 9));
                preparedStatement4.setInt(2, i);
                preparedStatement4.setInt(3, rs.getInt("boardID"));
                preparedStatement4.addBatch();
            }
            preparedStatement4.executeBatch();
            conn.commit();

        } catch (SQLException e) {
            logger.info(bundle.getString("WriteException"));
            throw new WriteDaoException(e);
        }


    }

    @Override
    public void close() throws Exception {
        try {
            conn.close();
        } catch (SQLException e) {
            logger.info(bundle.getString("SqldatabaseException"));
            throw new SqldatabaseException(e);
        }
    }

}
