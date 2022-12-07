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
        System.out.print("\n");
        SudokuBoard sb2 = sb.clone();
        sb2.getBox(2, 1).clone().getFields().add(new SudokuField());


            for (int i = 0; i < 9; i++) {

                for (int j = 0; j < 9; j++) {
                    System.out.print(sb2.getField(i, j) + "  ");
                }
                System.out.print("\n");
            }
            System.out.print("\n");
        System.out.println(sb);
        System.out.println(sb2);
        System.out.println(sb.equals(sb2));
        sb2.setField(0,0, 1);
        System.out.println("sb = " + sb.getField(0, 0) + "sb2 = " + sb2.getField(0,0));
    }
}
