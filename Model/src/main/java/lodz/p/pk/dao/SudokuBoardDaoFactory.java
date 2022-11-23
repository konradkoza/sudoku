package lodz.p.pk.dao;

import lodz.p.pk.sudoku.SudokuBoard;

public class SudokuBoardDaoFactory {

    public SudokuBoardDaoFactory() {
    }

    public Dao<SudokuBoard> getFileDao(String filename) {
        return new FileSudokuBoardDao(filename);
    }
}
