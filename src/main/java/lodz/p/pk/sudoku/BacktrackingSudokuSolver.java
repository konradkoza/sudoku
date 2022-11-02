package lodz.p.pk.sudoku;

import java.util.Random;

public class BacktrackingSudokuSolver implements SudokuSolver {


    public boolean fillBoard(SudokuBoard sudokuBoard) {
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                if (sudokuBoard.getField(row, column) == 0) {
                    for (int numberToTry = 1; numberToTry <= 9; numberToTry++) {
                        if (checkConditions(column, row, numberToTry, sudokuBoard)) {
                            sudokuBoard.setField(row, column, numberToTry);
                            if (fillBoard(sudokuBoard)) {
                                return true;
                            } else {
                                sudokuBoard.setField(row, column, 0);
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;

    }

    @Override
    public void solve(SudokuBoard sudokuBoard) {
        initFirstRow(sudokuBoard);
        fillBoard(sudokuBoard);

    }

    private void initFirstRow(SudokuBoard sudokuBoard) {
        Random rand = new Random();
        int x = rand.nextInt(1, 10);
        for (int i = 0; i < 9; i++) {
            while (sudokuBoard.getField(0, i) == 0) {
                if (checkRow(0, x, sudokuBoard)) {

                    sudokuBoard.setField(0, i, x);
                }
                x = rand.nextInt(1, 10);
            }
        }
    }

    private boolean checkRow(int row, int number, SudokuBoard sudokuBoard) {
        for (int i = 0; i < 9; i++) {
            if (sudokuBoard.getField(row, i) == number) {
                return false;
            }
        }
        return true;
    }

    private boolean checkColumn(int column, int number, SudokuBoard sudokuBoard) {
        for (int i = 0; i < 9; i++) {
            if (sudokuBoard.getField(i, column) == number) {
                return false;
            }
        }
        return true;
    }

    private boolean checkSquare(int column, int row, int number, SudokuBoard sudokuBoard) {
        int firstColumn = column - column % 3;
        int firstRow = row - row % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (sudokuBoard.getField(i + firstRow, j + firstColumn) == number) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkConditions(int column, int row, int number, SudokuBoard sudokuBoard) {
        return checkRow(row, number, sudokuBoard) && checkColumn(column, number, sudokuBoard)
                && checkSquare(column, row, number, sudokuBoard);
    }



}