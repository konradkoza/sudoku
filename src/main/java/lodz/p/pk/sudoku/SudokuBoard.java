package lodz.p.pk.sudoku;

import java.util.Random;

public class SudokuBoard {

    private final SudokuSolver sudokuSolver;
    private final int[][] board = new int[9][9];

    private boolean checkRow(int row, int number) {
        for (int i = 0; i < 9; i++) {
            if (getField(row, i) == number) {
                return false;
            }
        }
        return true;
    }

    private  boolean checkColumn(int column, int number) {
        for (int i = 0; i < 9; i++) {
            if (getField(i, column) == number) {
                return false;
            }
        }
        return true;
    }

    private boolean checkSquare(int column, int row, int number) {
        int firstColumn = column - column %  3;
        int firstRow = row - row % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (getField(i + firstRow,j + firstColumn) == number)  {
                    return false;
                }
            }
        }
        return true;
    }

    public  boolean checkConditions(int column, int row, int number) {
        return checkRow(row, number) && checkColumn(column, number)
                && checkSquare(column, row, number);
    }

    private void initFirstRow() {
        Random rand = new Random();
        int x = rand.nextInt(1, 10);
        for (int i = 0; i < board.length; i++) {
            while (board[0][i] == 0) {
                if (checkRow(0, x)) {

                    board[0][i] = x;
                }
                x = rand.nextInt(1, 10);
            }
        }
    }

    public void solveGame() {
        sudokuSolver.solve(this);
    }

    public SudokuBoard(SudokuSolver solver) {
        initFirstRow();
        sudokuSolver = solver;
    }




    public int getField(int i, int j)  {
        return board[i][j];
    }

    public void setField(int i, int j, int number) {
        board[i][j] = number;
    }
}

