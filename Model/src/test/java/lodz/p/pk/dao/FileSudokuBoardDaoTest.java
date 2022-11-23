package lodz.p.pk.dao;

import lodz.p.pk.sudoku.BacktrackingSudokuSolver;
import lodz.p.pk.sudoku.SudokuBoard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileSudokuBoardDaoTest {

    @Test
    void readAndWrite() {
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        Dao<SudokuBoard> fileDao;
        assertTrue(SudokuBoardDaoFactory.getFileDao("file") instanceof FileSudokuBoardDao);
        fileDao = SudokuBoardDaoFactory.getFileDao("file");

        fileDao.write(board);
        SudokuBoard board2 = fileDao.read();
        assertTrue(board.equals(board2));


    }


}