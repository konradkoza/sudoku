package lodz.p.pk.sudoku;

import java.util.HashSet;
import java.util.Set;

public class SudokuRow implements SudokuVerifier {
    private SudokuField[] row;

    public SudokuRow(SudokuField[] row) {
        this.row = row;

    }

    @Override
    public boolean verify() {
        Set<Integer> numbers = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                numbers.add(row[i].getValue());
            }

        }
        return numbers.size() == 9;
    }
}
