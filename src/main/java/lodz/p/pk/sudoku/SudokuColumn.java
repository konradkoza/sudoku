package lodz.p.pk.sudoku;

public class SudokuColumn implements SudokuVerifier {
    private SudokuField[] column = new SudokuField[9];

    public SudokuColumn(SudokuField[] column) {
        this.column = column;
    }

    @Override
    public boolean verify() {
        return false;
    }
}
