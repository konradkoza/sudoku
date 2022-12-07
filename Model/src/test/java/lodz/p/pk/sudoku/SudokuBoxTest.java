package lodz.p.pk.sudoku;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SudokuBoxTest {

    @Test
    void isCloneable() {
        final List<SudokuField> elements = Arrays.asList(new SudokuField[9]);
        SudokuBox sb = new SudokuBox(elements);
        SudokuBox sbClone = sb.clone();
        assertEquals(sb, sbClone);
        sbClone = null;
        assertNotEquals(sb, sbClone);
    }

}
