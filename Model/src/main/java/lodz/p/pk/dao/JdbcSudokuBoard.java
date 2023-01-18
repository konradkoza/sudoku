package lodz.p.pk.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import lodz.p.pk.exceptions.ReadDaoException;
import lodz.p.pk.exceptions.WriteDaoException;
import lodz.p.pk.sudoku.BacktrackingSudokuSolver;
import lodz.p.pk.sudoku.SudokuBoard;
import lodz.p.pk.sudoku.SudokuSolver;


public class JdbcSudokuBoard implements Dao<SudokuBoard>, AutoCloseable {

    private final String jdbcUrl = "jdbc:sqlite:SudokuDatabase.db";

    public JdbcSudokuBoard(String boardName) {
        this.boardName = boardName;
    }

    private String boardName;

    private Connection conn = null;

    private void executeSQl(String sqlQuery, Connection conn) {
        try (PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery)) {
           conn.setAutoCommit(false);
           preparedStatement.execute();
           conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public SudokuBoard read() throws ReadDaoException {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        try {
            conn = DriverManager.getConnection(jdbcUrl);
        } catch (SQLException e) {
            throw new RuntimeException(e); //dodac własny wyjatek
        }

        String sqlValues = "SELECT value FROM SudokuFields" + boardName;

        try (Statement statement1 = conn.createStatement();
          ResultSet rs = statement1.executeQuery(sqlValues)) {

            conn.setAutoCommit(false);
            while (rs.next()) {
                //                System.out.println("\n"+rs.getRow());
                board.setField((int)((rs.getRow() - 1) / 9),
                        (rs.getRow() - 1)  % 9, rs.getInt("value"));
            }
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return board;
    }

    @Override
    public void write(SudokuBoard obj) throws WriteDaoException {
        try {
            conn = DriverManager.getConnection(jdbcUrl);
        } catch (SQLException e) {
            throw new RuntimeException(e); //dodac własny wyjatek
        }
        // sql queries do napisania
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
            throw new RuntimeException(e);
        }

        try (
             PreparedStatement preparedStatement4 = conn.prepareStatement(sqlFieldsInsert);
             ResultSet rs = conn.createStatement().executeQuery(selectID);
        ) {
            conn.setAutoCommit(false);

            for (int i = 0; i < 81; i++) {
                preparedStatement4.setInt(1, obj.getField((int)(i / 9),i % 9));
                //                System.out.print("i: "+ i / 9 +" j: "+ i % 9+ "\n");
                preparedStatement4.setInt(2, i);
                preparedStatement4.setInt(3, rs.getInt("boardID"));
                preparedStatement4.addBatch();
            }
            preparedStatement4.executeBatch();
            conn.commit();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void close() throws Exception {
        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e); // dodac wyjatek
        }
    }

}
