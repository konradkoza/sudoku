package lodz.p.pk.sudoku;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SudokuColumnTest {

    @Test
    void isCloneable() {
        final List<SudokuField> elements = Arrays.asList(new SudokuField[9]);
        SudokuColumn sc = new SudokuColumn(elements);
        SudokuColumn scClone = sc.clone();
        assertEquals(sc, scClone);
        scClone = null;
        assertNotEquals(sc, scClone);
    }
}
