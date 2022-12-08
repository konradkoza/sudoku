package lodz.p.pk.sudoku;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SudokuColumnTest {

    @Test
    void isCloneable() {


        SudokuSolver ss = new BacktrackingSudokuSolver();
        SudokuBoard sb1 = new SudokuBoard(ss);
        sb1.solveGame();
        SudokuColumn sc1 = sb1.getColumn(1);
        sb1.getColumn(1);
        SudokuColumn sc2 = sc1.clone();
        assertEquals(sc1, sc2);
        sb1.setField(1,1,0);
        assertNotEquals(sc1.getFields().get(1), sc2.getFields().get(1));
        assertNotEquals(sc1, sc2);
    }
}
