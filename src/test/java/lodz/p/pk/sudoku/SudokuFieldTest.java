package lodz.p.pk.sudoku;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;


public class SudokuFieldTest {
    @Test
    void standardMethods(){
        SudokuField sf = new SudokuField();
        SudokuField sf2 = new SudokuField();

        assertNotNull(sf.toString());
        assertEquals(sf.hashCode(), sf2.hashCode());
        assertTrue(sf.equals(sf));
        assertFalse(sf.equals(null));
        assertFalse(sf.equals(1));
        assertTrue(sf.equals(sf2));
        sf2.setValue(1);
        assertFalse(sf.equals(sf2));
    }
}
