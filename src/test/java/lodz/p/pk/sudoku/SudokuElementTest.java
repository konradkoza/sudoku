package lodz.p.pk.sudoku;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuElementTest {

    @Test
    void standardMethods(){
        List<SudokuField> fields = Arrays.asList(new SudokuField[9]);
        List<SudokuField> fields2 = Arrays.asList(new SudokuField[8]);
        SudokuElement se = new SudokuRow(fields);
        SudokuElement se2 = new SudokuRow(fields);
        SudokuElement se3 = new SudokuRow(fields2);

        assertNotNull(se.toString());
        assertEquals(se.hashCode(), se2.hashCode());
        assertTrue(se.equals(se));
        assertFalse(se.equals(null));
        assertFalse(se.equals(1));
        assertTrue(se.equals(se2));
        assertFalse(se.equals(se3));
    }
}
