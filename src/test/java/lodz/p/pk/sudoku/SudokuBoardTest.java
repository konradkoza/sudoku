package lodz.p.pk.sudoku;

import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.*;


class SudokuBoardTest {
    @Test
    void isBoardDifferent(){
        SudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sb1 = new SudokuBoard(sudokuSolver);
        SudokuBoard sb2 = new SudokuBoard(sudokuSolver);
        sb1.solveGame();
        sb2.solveGame();
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
        sb.solveGame();
        for(int i = 0; i < 9; i++) {
            assertTrue(sb.getRow(i).verify());
            assertTrue(sb.getColumn(i).verify());
            assertTrue(sb.getBox(i % 3, i / 3).verify());
        }
    }

    @Test
    void wrongSolutionTest(){
        SudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sb = new SudokuBoard(sudokuSolver);
        sb.solveGame();
        sb.setField(0, 0, 1);
        sb.setField(0, 1, 1);
        sb.setField(1, 0, 1);
        assertFalse(sb.getRow(0).verify());
        assertFalse(sb.getColumn(0).verify());
        assertFalse(sb.getBox(0, 0).verify());
        sb.solveGame();
    }

    @Test
    void observerTest(){
        SudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard observable = new SudokuBoard(sudokuSolver);
        Observer observer = new Observer();

        observable.addPropertyChangeListener(observer);
        observable.solveGame();
        assertTrue(observer.isCorrect());

        observable.setField(0, 0, 1);
        observable.setField(0, 1, 1);
        assertFalse(observer.isCorrect());
    }
}