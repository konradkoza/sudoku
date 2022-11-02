package lodz.p.pk.sudoku;



public class SudokuBoard {

    private final SudokuSolver sudokuSolver;
    private final SudokuField[][] board = new SudokuField[9][9];

    private boolean checkBoard() {
        boolean isCorrect = true;
        for (int i = 0; i < 9; i++) {
            if (!(getRow(i).verify() && getColumn(i).verify() && getBox(i % 3, i / 3).verify())) {
                isCorrect = false;
            }
        }
        return isCorrect;
    }

    public SudokuRow getRow(int y) {
        SudokuField[] fields = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            fields[i] = board[y][i];
        }
        return new SudokuRow(fields);
    }

    public SudokuColumn getColumn(int x) {
        SudokuField[] fields = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            fields[i] = board[i][x];
        }
        return new SudokuColumn(fields);
    }

    public SudokuBox getBox(int x, int y) {
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
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = new SudokuField();
            }

        }

        sudokuSolver = solver;
    }




    public int getField(int i, int j)  {
        return board[i][j].getValue();
    }

    public void setField(int i, int j, int number) {
        board[i][j].setValue(number);
    }
}

