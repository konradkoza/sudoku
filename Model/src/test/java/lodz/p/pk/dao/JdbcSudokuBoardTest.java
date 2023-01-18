package lodz.p.pk.dao;

import lodz.p.pk.exceptions.WriteDaoException;
import lodz.p.pk.sudoku.BacktrackingSudokuSolver;
import lodz.p.pk.sudoku.SudokuBoard;
import org.junit.jupiter.api.Test;

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
    }
}