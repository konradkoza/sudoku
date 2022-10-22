package lodz.p.pk.sudoku;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


class SudokuBoardTest {
    @Test
    void isBoardDifferent(){
        SudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sb1 = new SudokuBoard(sudokuSolver);
        SudokuBoard sb2 = new SudokuBoard(sudokuSolver);
        sb1.fillBoard();
        sb2.fillBoard();
        boolean isDifferent = false;
        for (int i = 0; i < 9; i++) {
            if(sb1.getField(0, i) != sb2.getField(0, i)){
                isDifferent = true;
                break;
            }
        }
        assertTrue(isDifferent);
    }

    @Test
    void solutionTest(){
        SudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sb = new SudokuBoard(sudokuSolver);
        sb.fillBoard();
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
                startCol += 3;
            }
            startCol = 0;
            startRow += 3;
        }
    }
}