package lodz.p.pk.sudoku;

public class SudokuBox implements SudokuVerifier{
    private SudokuField[][] box = new SudokuField[3][3];

    public SudokuBox(SudokuField[][] box) {
        this.box = box;
    }

    @Override
    public boolean verify() {

        return false;
    }
}
