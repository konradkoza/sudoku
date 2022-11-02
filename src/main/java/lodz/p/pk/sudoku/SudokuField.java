package lodz.p.pk.sudoku;

public class SudokuField {
    private int value;

    public SudokuField() {
        this.value = 0;
    }

    public SudokuField(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
