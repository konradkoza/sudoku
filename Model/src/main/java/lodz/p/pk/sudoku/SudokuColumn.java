package lodz.p.pk.sudoku;


import java.util.List;

public class SudokuColumn extends SudokuElement implements Cloneable {

    public SudokuColumn(List<SudokuField> fields) {
        super(fields);
    }

    @Override
    public SudokuColumn clone() {
        return new SudokuColumn(getFields());
    }
}
