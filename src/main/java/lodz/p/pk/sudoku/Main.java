package lodz.p.pk.sudoku;

/**
 * Hello world!.
 *
 */

public class Main {
    public static void main(String[] args) {
        SudokuBoard sb = new SudokuBoard();
        sb.initFirstRow();


        sb.printBoard();
        System.out.println("\nWartosc logiczna " + sb.fillBoard() + "\n");
        sb.printBoard();
    }
}
