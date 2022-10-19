package lodz.p.pk.sudoku;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


class SudokuBoardTest {
    @Test
    void isBoardDifferent(){
        SudokuBoard sb1 = new SudokuBoard();
        SudokuBoard sb2 = new SudokuBoard();
        sb1.fillBoard();
        sb2.fillBoard();
        sb2.printBoard();
        System.out.println();
        sb1.printBoard();
        boolean isDifferent = false;
        for (int i = 0; i < 9; i++) {
            if(sb1.getField(0, i) != sb2.getField(0, i)){
                isDifferent = true;
            }

        }
        assertTrue(isDifferent);

    }
    @Test
    void solutionTest(){
        SudokuBoard sb = new SudokuBoard();
        sb.fillBoard();
        sb.printBoard();
        Set<Integer> numbers = new HashSet<Integer>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                numbers.add(sb.getField(i, j));
            }
            assertTrue(numbers.size() == 9);
            numbers.clear();
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                numbers.add(sb.getField(j, i));
            }
            assertTrue(numbers.size() == 9);
            numbers.clear();
        }
        int startRow = 0;
        int startCol = 0;

        for (int l = 0; l < 3; l++) {
            for (int k = 0; k < 3; k++) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        numbers.add(sb.getField(i + startRow, j + startCol));
                    }
                }
                assertTrue(numbers.size() == 9);
                numbers.clear();
                if(startCol < 6) {
                    startCol += 3;
                }
            }
            if(startRow < 6) {
                startRow += 3;
            }
        }


    }

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
        boolean areIdentical = false;
        sb1.fillBoard();
        sb2.fillBoard();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int number1 = sb1.getField(i, j);
                int number2 = sb2.getField(i, j);
                if(number1 == number2) {
                    areIdentical = false;
                    break;
                }
            }
        }
        assertTrue(!areIdentical);
    }
}