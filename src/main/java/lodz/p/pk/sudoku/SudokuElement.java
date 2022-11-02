package lodz.p.pk.sudoku;

abstract public class SudokuElement {

    private SudokuField[] fields;

    public SudokuElement(SudokuField[] fields) {
        this.fields = fields;
    }

    public boolean verify() {
        for (int i = 0; i < 9; i++) {
            for (int j = i + 1; j < 9; j++) {
                if (fields[i].getValue() == fields[j].getValue() && fields[i].getValue() != 0) {
                    return false;
                }
            }
        }
        return true;
    }

}
