package lodz.p.pk.dao;

import lodz.p.pk.exceptions.ReadDaoException;
import lodz.p.pk.exceptions.WriteDaoException;
import lodz.p.pk.sudoku.SudokuBoard;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class JdbcSudokuBoard implements Dao<SudokuBoard>, AutoCloseable {

    private final String jdbcUrl = "jdbc:sqlite:SudokuDatabase.db";

    public JdbcSudokuBoard(String boardName) {
        this.boardName = boardName;
    }

    private String boardName;

    private Connection conn = null;


    @Override
    public SudokuBoard read() throws ReadDaoException {
        return null;
    }

    @Override
    public void write(SudokuBoard obj) throws WriteDaoException {
        try {
            conn = DriverManager.getConnection(jdbcUrl);
        } catch (SQLException e) {
            throw new RuntimeException(e); //dodac własny wyjatek
        }
        // sql queries do napisania
        String sqlBoardTable = "CREATE TABLE IF NOT EXISTS boards"
                + " (boardID text PRIMARY KEY NOT NULL, boardName text";
        String sqlBoardTableInsert = "INSERT INTO boards (boardName, 12) values (?, ?)";
        String sqlFieldsTable = "";



        try (PreparedStatement preparedStatement1 = conn.prepareStatement(sqlBoardTable);
             PreparedStatement preparedStatement2 = conn.prepareStatement(sqlFieldsTable);
             PreparedStatement preparedStatement3 = conn.prepareStatement(sqlBoardTableInsert)) {
            preparedStatement1.setString(1, boardName);
            preparedStatement1.execute();

            preparedStatement2.setString(1, "x"); // dodawanie pol
            preparedStatement2.execute();
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
