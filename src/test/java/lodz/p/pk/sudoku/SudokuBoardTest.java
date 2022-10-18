package lodz.p.pk.sudoku;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardTest {

    @Test
    void getBoard() {
        SudokuBoard sb = new SudokuBoard();
          sb.printBoard();
//        assertEquals(0, sb.getBoard(0,0));
//        assertEquals(4,sb.getBoard(2,2));
//        assertEquals(24, sb.getBoard(4, 6));
    }
}