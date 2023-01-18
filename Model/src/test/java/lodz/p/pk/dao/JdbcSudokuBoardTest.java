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
        SudokuBoard board1 = null;
        try(Dao<SudokuBoard> jdbcDao = SudokuBoardDaoFactory.getJdbcDao("testBoard");) {
            board1 = jdbcDao.read();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board.getField(i, j) + "  ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board1.getField(i, j) + "  ");
            }
            System.out.print("\n");
        }
        System.out.println(board.equals(board1));
    }
}