package lodz.p.pk.dao;

import lodz.p.pk.exceptions.ReadDaoException;
import lodz.p.pk.exceptions.WriteDaoException;
import lodz.p.pk.sudoku.BacktrackingSudokuSolver;
import lodz.p.pk.sudoku.SudokuBoard;
import lodz.p.pk.sudoku.SudokuSolver;

import javax.xml.transform.Result;
import java.sql.*;

public class JdbcSudokuBoard implements Dao<SudokuBoard>, AutoCloseable {

    private final String jdbcUrl = "jdbc:sqlite:SudokuDatabase.db";

    public JdbcSudokuBoard(String boardName) {
        this.boardName = boardName;
    }

    private String boardName;

    private Connection conn = null;

    private void executeSQl(String sqlQuery, Connection conn){
        try(PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery)){
           preparedStatement.execute();
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

        try (Statement statement1 = conn.createStatement()){

            ResultSet rs = statement1.executeQuery(sqlValues);
            while (rs.next()){
                board.setField((rs.getRow()) % 9, (int)((rs.getRow()) / 9), rs.getInt("value"));
            }

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
        String sqlBoardTable = "CREATE TABLE IF NOT EXISTS boards " +
                "(boardID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, boardName TEXT, UNIQUE(boardName))";

        String sqlBoardTableInsert = "INSERT OR IGNORE INTO boards (boardName) values (?)";

        String sqlDropFields = "DROP TABLE IF EXISTS SudokuFields" + boardName;
        String sqlFieldsTable =  "CREATE TABLE IF NOT EXISTS "+ "SudokuFields" + boardName +
                "(value INTEGER, position INTEGER, boardID INTEGER, FOREIGN KEY(boardID) REFERENCES boards(boardID))";

        String sqlFieldsInsert = "INSERT INTO SudokuFields" + boardName + "(value, position, boardID) values (?,?,?)";
        String selectID = "SELECT boardID FROM boards ORDER BY boardID DESC LIMIT 1";

        executeSQl(sqlBoardTable, conn);
        executeSQl(sqlDropFields, conn);
        executeSQl(sqlFieldsTable, conn);

        try (PreparedStatement preparedStatement2 = conn.prepareStatement(sqlBoardTableInsert)){
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
                preparedStatement4.setInt(1, obj.getField(i % 9, (int)(i / 9)));
                System.out.print("i: "+ i % 9 +"j: "+ i / 9);
                preparedStatement4.setInt(2, i);
                preparedStatement4.setInt(3, rs.getInt("boardID"));
                preparedStatement4.executeUpdate();
                conn.commit();
            }


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
