package lodz.p.pk.sudoku;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SudokuRowTest {

    @Test
    void isCloneable() {
        SudokuSolver ss = new BacktrackingSudokuSolver();
        SudokuBoard sb = new SudokuBoard(ss);
        sb.solveGame();
        SudokuRow srow = sb.getRow(1);
        SudokuRow srowClone = srow.clone();
        assertEquals(srow, srowClone);
        sb.setField(1,1, 0);
        assertNotEquals(srow, srowClone);
    }
}
