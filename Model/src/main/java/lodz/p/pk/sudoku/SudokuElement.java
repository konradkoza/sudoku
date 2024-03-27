package lodz.p.pk.sudoku;

import com.google.common.base.Objects;
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
                if (fields.get(i).getValue() == fields.get(j).getValue() & fields.get(i).getValue() != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    protected List<SudokuField> getFields() {
        List<SudokuField> sudokuFields = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < 9; i++) {
            sudokuFields.set(i, fields.get(i).clone());
        }
        return sudokuFields;
    }

    @Override
    public String toString() {
        return "SudokuElement{"
                + "fields=" + fields
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
        SudokuElement that = (SudokuElement) o;
        return Objects.equal(fields, that.fields);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(fields);
    }


}
