package lodz.p.pk.sudoku;


import java.util.List;

public class SudokuRow extends SudokuElement implements Cloneable {

    public SudokuRow(List<SudokuField> fields) {
        super(fields);
    }

    @Override
    public SudokuRow clone() {
        return new SudokuRow(getFields());
    }
}
