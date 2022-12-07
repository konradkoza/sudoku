package lodz.p.pk.sudoku;

import org.junit.jupiter.api.Assertions;
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

    @Test
    void isCloneable() {
        SudokuField sf = new SudokuField();
        SudokuField sfClone = sf.clone();
        assertEquals(sf, sfClone);
        sfClone.setValue(999);
        assertNotEquals(sf, sfClone);
    }

    @Test
    void isComparable() {
        SudokuField sf = new SudokuField();
        SudokuField sf2 = new SudokuField();

        assertEquals(sf.compareTo(sf2), 0);
        sf.setValue(999);
        assertEquals(sf.compareTo(sf2), 1);
        sf.setValue(-999);
        assertEquals(sf.compareTo(sf2), -1);

        NullPointerException thrown = Assertions.assertThrows(NullPointerException.class, () -> {
            sf.compareTo(null);
        });
    }
}
