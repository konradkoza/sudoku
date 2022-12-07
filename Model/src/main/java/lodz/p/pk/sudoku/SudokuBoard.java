package lodz.p.pk.sudoku;


import com.google.common.base.Objects;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class SudokuBoard implements Serializable, Cloneable {


    private final SudokuSolver sudokuSolver;

    private final List<SudokuField> board = Arrays.asList(new SudokuField[81]);
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
        List<SudokuField> fields = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < 9; i++) {
            fields.set(i,  board.get(y * 9 + i));
        }
        return new SudokuRow(fields);
    }

    public SudokuColumn getColumn(int x) {
        List<SudokuField> fields = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < 9; i++) {
            fields.set(i,  board.get(x + i * 9));
        }
        return new SudokuColumn(fields);
    }

    public SudokuBox getBox(int x, int y) {
        List<SudokuField> fields = Arrays.asList(new SudokuField[9]);
        int startRow = 3 * x;
        int startCol = 3 * y;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                fields.set(i * 3 + j, board.get(startRow * 9 + startCol + i * 9 + j));
            }
        }
        return new SudokuBox(fields);
    }



    public void solveGame() {
        sudokuSolver.solve(this);
    }

    public SudokuBoard(SudokuSolver solver) {
        support = new PropertyChangeSupport(this);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board.set(i * 9 + j, new SudokuField());
            }
        }
        sudokuSolver = solver;
    }


    public int getField(int i, int j)  {
        return board.get(i * 9 + j).getValue();
    }

    public void setField(int i, int j, int number) {
        boolean checkBeforeChange = this.checkBoard();
        board.get(i * 9 + j).setValue(number);
        support.firePropertyChange("isCorrect", checkBeforeChange, this.checkBoard());
    }

    @Override
    public String toString() {
        return "SudokuBoard{"
                //"sudokuSolver=" + sudokuSolver +
                + "board=" + board
                + '}';
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SudokuBoard that = (SudokuBoard) o;
        return Objects.equal(board, that.board);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(board);
    }

    @Override
    public SudokuBoard clone() {
        SudokuBoard sbClone = new SudokuBoard(sudokuSolver);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sbClone.setField(i, j, this.getField(i, j));
            }
        }
        return sbClone;
    }
}

