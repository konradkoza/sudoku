package lodz.p.pk.sudoku;

import java.util.Arrays;
import java.util.List;

abstract class SudokuElement {

    private final List<SudokuField> fields;

    public SudokuElement(List<SudokuField> fields) {
        this.fields = fields;
    }

    public boolean verify() {
        for (int i = 0; i < 9; i++) {
            for (int j = i + 1; j < 9; j++) {
                if (fields.get(i).getValue() == fields.get(j).getValue()) {
                    return false;
                }
            }
        }
        return true;
    }

}
