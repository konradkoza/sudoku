package lodz.p.pk.sudoku;

import java.util.Random;


public class SudokuBoard {

    private final SudokuSolver sudokuSolver;
    private final SudokuField[][] board = new SudokuField[9][9];

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
            while (board[0][i].getValue() == 0) {
                if (checkRow(0, x)) {

                    board[0][i].setValue(x);
                }
                x = rand.nextInt(1, 10);
            }
        }
    }
    public SudokuRow getRow(int y){
        SudokuField[] fields = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            fields[i] = board[y][i];
        }
        return new SudokuRow(fields);
    }

    public SudokuColumn getColumn(int x){
        SudokuField[] fields = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            fields[i] = board[i][x];
        }
        return new SudokuColumn(fields);
    }

    public SudokuBox getBox(int x, int y){
        SudokuField[][] fields = new SudokuField[3][3];
        int startRow = 3 * x;
        int startCol = 3 * y;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                fields[i][j] = board[startRow + i][startCol + j];
            }
        }
        return new SudokuBox(fields);
    }



    public void solveGame() {
        sudokuSolver.solve(this);
    }

    public SudokuBoard(SudokuSolver solver) {
        initFirstRow();
        sudokuSolver = solver;
    }




    public int getField(int i, int j)  {
        return board[i][j].getValue();
    }

    public void setField(int i, int j, int number) {
        board[i][j].setValue(number);
    }
}

