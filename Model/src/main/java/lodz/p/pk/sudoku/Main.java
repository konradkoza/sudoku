package lodz.p.pk.sudoku;


import lodz.p.pk.dao.Dao;
import lodz.p.pk.dao.SudokuBoardDaoFactory;

public class Main {
    public static void main(String[] args) {
        SudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sb = new SudokuBoard(sudokuSolver);
        SudokuBoardDaoFactory daoFactory = new SudokuBoardDaoFactory();
        Dao<SudokuBoard> dao = daoFactory.getFileDao("file");

        dao.write(sb);

        sb.solveGame();

        for (int i = 0; i < 9; i++) {

            for (int j = 0; j < 9; j++) {
                System.out.print(sb.getField(i, j) + "  ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
        sb = dao.read();
        for (int i = 0; i < 9; i++) {

            for (int j = 0; j < 9; j++) {
                System.out.print(sb.getField(i, j) + "  ");
            }
            System.out.print("\n");
        }

        sb.solveGame();
        System.out.print("\n");
        for (int i = 0; i < 9; i++) {

            for (int j = 0; j < 9; j++) {
                System.out.print(sb.getField(i, j) + "  ");
            }
            System.out.print("\n");
        }
    }
}
