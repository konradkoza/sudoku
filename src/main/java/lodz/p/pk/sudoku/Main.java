package lodz.p.pk.sudoku;


public class Main {
    public static void main(String[] args) {
        SudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sb = new SudokuBoard(sudokuSolver);
        sb.solveGame();
        for (int i = 0; i < 9; i++) {

            for (int j = 0; j < 9; j++) {
                System.out.print(sb.getField(i, j) + "  ");
            }
            System.out.print("\n");
        }
    }
}
