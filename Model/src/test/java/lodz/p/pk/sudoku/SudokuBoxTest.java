package lodz.p.pk.sudoku;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SudokuBoxTest {

    @Test
    void isCloneable() {
        SudokuSolver ss = new BacktrackingSudokuSolver();
        SudokuBoard sb = new SudokuBoard(ss);
        sb.solveGame();
        SudokuBox sbox = sb.getBox(0,0);
        SudokuBox sboxClone = sbox.clone();
        assertEquals(sbox, sboxClone);
        sb.setField(1,1, 0);
        assertNotEquals(sbox, sboxClone);
    }

}
