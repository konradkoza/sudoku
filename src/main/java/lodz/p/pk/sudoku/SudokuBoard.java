package lodz.p.pk.sudoku;

import java.util.Random;

public class SudokuBoard {



    private int[][] board = new int[9][9];

    private boolean checkRow(int[][] array, int row, int number) {
        for (int i = 0; i < array.length; i++) {
            if (array[row][i] == number) {
                return false;
            }
        }
        return true;
    }

    private  boolean checkColumn(int[][] array, int column, int number) {
        for (int i = 0; i < array.length; i++) {
            if (array[i][column] == number) {
                return false;
            }
        }
        return true;
    }

    private boolean checkSquare(int[][] array, int column, int row, int number) {
        int firstColumn = column - column %  3;
        int firstRow = row - row % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (array[i + firstRow][j + firstColumn] == number) {
                    return false;
                }
            }
        }
        return true;
    }
    private  boolean checkConditions(int[][] array, int column, int row, int number) {
        return checkRow(array, row, number) && checkColumn(array, column, number) && checkSquare(array, column, row, number);
    }
    private void initFirstRow() {
        Random rand = new Random();
        int x = rand.nextInt(1, 10);
        for (int i = 0; i < board.length; i++) {
            while (board[0][i] == 0) {
                if (checkRow(board, 0, x)) {

                    board[0][i] = x;
                }
                x = rand.nextInt(1, 10);
            }
        }
    }

    private void fillBoard(){
        initFirstRow();
        
    }

    public SudokuBoard() {
    }

    public void printBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + "  ");
            }
            System.out.print("\n");


        }
    }

    public int getBoard(int i, int j)  {
        return board[i][j];
    }

}
