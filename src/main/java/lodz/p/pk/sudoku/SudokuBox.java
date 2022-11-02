package lodz.p.pk.sudoku;

import java.util.HashSet;
import java.util.Set;

public class SudokuBox implements SudokuVerifier {
    private SudokuField[][] box;

    public SudokuBox(SudokuField[][] box) {
        this.box = box;
    }

    @Override
    public boolean verify() {
        Set<Integer> numbers = new HashSet<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                numbers.add(box[i][j].getValue());
            }
        }
        return numbers.size() == 9;
    }
}
