package lodz.p.pk.sudoku;

import java.util.HashSet;
import java.util.Set;

public class SudokuColumn implements SudokuVerifier {
    private SudokuField[] column;

    public SudokuColumn(SudokuField[] column) {
        this.column = column;
    }

    @Override
    public boolean verify() {
        Set<Integer> numbers = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                numbers.add(column[i].getValue());
            }

        }
        return numbers.size() == 9;
    }

}
