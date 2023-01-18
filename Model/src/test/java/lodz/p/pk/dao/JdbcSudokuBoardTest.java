package lodz.p.pk.dao;

import lodz.p.pk.exceptions.SqldatabaseException;
import lodz.p.pk.exceptions.WriteDaoException;
import lodz.p.pk.sudoku.BacktrackingSudokuSolver;
import lodz.p.pk.sudoku.SudokuBoard;
import lodz.p.pk.sudoku.SudokuSolver;
import org.junit.jupiter.api.Test;

import java.util.MissingResourceException;

import static org.junit.jupiter.api.Assertions.*;

class JdbcSudokuBoardTest {
    @Test
    void writeTest(){
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        board.solveGame();

        try(Dao<SudokuBoard> jdbcDao = SudokuBoardDaoFactory.getJdbcDao("testBoard");) {
            jdbcDao.write(board);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        SudokuBoard board1 = null;
        try(Dao<SudokuBoard> jdbcDao = SudokuBoardDaoFactory.getJdbcDao("testBoard");) {
            board1 = jdbcDao.read();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertTrue(board.equals(board1));
    }

    @Test
    void exceptionTest() {
        assertDoesNotThrow(() ->{
            Dao<SudokuBoard> jdbcDao = SudokuBoardDaoFactory.getJdbcDao("testBoard");
            BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
            SudokuBoard sb = new SudokuBoard(solver);
            SudokuBoard sb2;
            jdbcDao.read();
            jdbcDao.write(sb);
            sb2 = jdbcDao.read();
            jdbcDao.close();
        } );
    }
}
