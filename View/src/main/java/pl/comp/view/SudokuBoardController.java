package pl.comp.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import lodz.p.pk.sudoku.BacktrackingSudokuSolver;
import lodz.p.pk.sudoku.SudokuBoard;
import lodz.p.pk.sudoku.SudokuSolver;

public class SudokuBoardController {

    @FXML
    private TextField f00;

    public void fillBoard(DifficultyLevel level) {

        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        board.solveGame();
        level.deleteFields(board);


    }
}
