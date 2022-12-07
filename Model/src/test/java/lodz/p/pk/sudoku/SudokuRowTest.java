package lodz.p.pk.sudoku;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SudokuRowTest {

    @Test
    void isCloneable() {
        final List<SudokuField> elements = Arrays.asList(new SudokuField[9]);
        SudokuRow sr = new SudokuRow(elements);
        SudokuRow srClone = sr.clone();
        assertEquals(sr, srClone);
        srClone = null;
        assertNotEquals(sr, srClone);
    }
}
