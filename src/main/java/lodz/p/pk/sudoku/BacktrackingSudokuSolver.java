package lodz.p.pk.sudoku;

import lodz.p.pk.sudoku.SudokuSolver;

public class BacktrackingSudokuSolver implements SudokuSolver {

    @Override
    public boolean solve(SudokuBoard sudokuBoard) {
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                if (sudokuBoard.getField(row,column) == 0) {
                    for (int numberToTry = 1; numberToTry <= 9; numberToTry++) {
                        if (sudokuBoard.checkConditions(column, row, numberToTry)) {
                            sudokuBoard.setField(row,column,numberToTry);
                            if (solve(sudokuBoard)) {
                                return true;
                            } else {
                                sudokuBoard.setField(row,column,0);
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;

    }
}
