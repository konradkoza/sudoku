package lodz.p.pk.sudoku;


import java.util.List;

public class SudokuBox extends SudokuElement implements Cloneable {

    public SudokuBox(List<SudokuField> fields) {
        super(fields);
    }

    @Override
    public SudokuBox clone() {
        return new SudokuBox(getFields());
    }
}
