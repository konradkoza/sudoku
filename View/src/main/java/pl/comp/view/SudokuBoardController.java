package pl.comp.view;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import lodz.p.pk.sudoku.BacktrackingSudokuSolver;
import lodz.p.pk.sudoku.SudokuBoard;
import lodz.p.pk.sudoku.SudokuSolver;

public class SudokuBoardController {

    @FXML
    private GridPane sudokuGrid;

    private DifficultyLevel diffLevel = DifficultyLevel.MEDIUM;

    public void setDiffLevel(DifficultyLevel diffLevel) {
        this.diffLevel = diffLevel;
    }


    public void initialize() {
//        TextField textField = new TextField();
//        textField.alignmentProperty().set(Pos.CENTER);
//        textField.setPrefWidth(46);
//        textField.setPrefHeight(42);
//        textField.textProperty().set("1");
//        sudokuGrid.add(textField, 1 , 1);
//
//
//        textField.alignmentProperty().set(Pos.CENTER);

        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        board.solveGame();
        diffLevel.deleteFields(board);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board.getField(i,j));

            }
            System.out.print("\n");
        }


    }


}
