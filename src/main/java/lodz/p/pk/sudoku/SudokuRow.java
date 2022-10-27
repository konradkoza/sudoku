package lodz.p.pk.sudoku;

public class SudokuRow implements SudokuVerifier {
    private SudokuField[] row = new SudokuField[9];

    public SudokuRow(SudokuField[] row) {
        this.row = row;

    }

    @Override
    public boolean verify() {
        return false;
    }
}
