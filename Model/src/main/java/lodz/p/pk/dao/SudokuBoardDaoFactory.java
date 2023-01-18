package lodz.p.pk.dao;

import lodz.p.pk.sudoku.SudokuBoard;

public class SudokuBoardDaoFactory {

    public static Dao<SudokuBoard> getFileDao(String filename) {
        return new FileSudokuBoardDao(filename);
    }

    public static Dao<SudokuBoard> getJdbcDao(String boardName){
        return new JdbcSudokuBoard(boardName);
    }
}
