package lodz.p.pk.sudoku;


import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SudokuBoard {


    private final SudokuSolver sudokuSolver;
    private final SudokuField[][] board = new SudokuField[9][9];

    private final PropertyChangeSupport support;

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    //    public void removePropertyChangeListener(PropertyChangeListener pcl) {
    //        support.removePropertyChangeListener(pcl);
    //    }

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
        SudokuField[] fields = new SudokuField[9];
        int startRow = 3 * x;
        int startCol = 3 * y;
        for (int i = 0; i < 9; i++) {
            fields[i] = board[startRow + i % 3][startCol + i / 3];
        }
        return new SudokuBox(fields);
    }



    public void solveGame() {
        sudokuSolver.solve(this);
        checkBoard();
    }

    public SudokuBoard(SudokuSolver solver) {
        support = new PropertyChangeSupport(this);
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
        boolean checkBeforeChange = this.checkBoard();
        board[i][j].setValue(number);
        support.firePropertyChange("isCorrect", checkBeforeChange, this.checkBoard());
    }
}

