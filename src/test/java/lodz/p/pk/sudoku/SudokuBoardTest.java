package lodz.p.pk.sudoku;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardTest {


    @Test
    void isSolutionValid() {
        SudokuBoard sb = new SudokuBoard();
        sb.fillBoard();
        sb.printBoard();
        boolean eachNumberValid = true;
        boolean eachRowValid = true;
        boolean eachColumnValid = true;
        boolean eachBoxValid = true;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                eachNumberValid = (0 < sb.getField(i, j) || sb.getField(i, j) < 10);
                if(i % 3 == 0 || j % 3 == 0) {
                    eachBoxValid = !sb.checkForIdenticalBoxValues(i, j);
                }
            }
            eachRowValid = !sb.checkForIdenticalRowValues(i);
            eachColumnValid = !sb.checkForIdenticalColumnValues(i);
            if(!(eachRowValid && eachColumnValid) && eachNumberValid && eachBoxValid) break;
        }
        assertTrue(eachRowValid && eachColumnValid && eachNumberValid && eachBoxValid);
    }

    @Test
    void areTwoBoardsDifferent() {
        SudokuBoard sb1 = new SudokuBoard();
        SudokuBoard sb2 = new SudokuBoard();
        sb1.fillBoard();
        sb2.fillBoard();
        assertTrue(sb1 != sb2);
    }

    void isWrongSolutionWrong() {

    }
}