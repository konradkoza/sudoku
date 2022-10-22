package lodz.p.pk.sudoku;

/**
 * Hello world!.
 *
 */

public class Main {
    public static void main(String[] args) {
        SudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sb = new SudokuBoard(sudokuSolver);
        sb.solveGame();
        sb.printBoard();
    }
}
