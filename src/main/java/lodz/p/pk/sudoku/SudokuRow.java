package lodz.p.pk.sudoku;

public class SudokuRow implements SudokuVerifier{
    private SudokuField[] row = new SudokuField[9];

    public SudokuRow(SudokuField[] row) {
        this.row = row;
//        for (int i = 0; i < 9; i++) {
//            row[i] = fields[i];
//        };
    }

    @Override
    public boolean verify() {
        return false;
    }
}
