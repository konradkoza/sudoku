package pl.comp.view;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
        TextField textField = new TextField();
        textField.alignmentProperty().set(Pos.CENTER);
        textField.setPrefWidth(46);
        textField.setPrefHeight(42);
        textField.textProperty().set("1");
        textField.alignmentProperty().set(Pos.CENTER);
        textField.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                if(!textField.getText().matches("[1-9]")){
                    textField.setText("0");
                }
            }

        });
        sudokuGrid.add(textField, 1 , 1);

        TextField textField1 = new TextField();
        textField1.alignmentProperty().set(Pos.CENTER);
        textField1.setPrefWidth(46);
        textField1.setPrefHeight(42);
        textField1.textProperty().set("1");
        textField1.alignmentProperty().set(Pos.CENTER);
        BooleanProperty isValid = new SimpleBooleanProperty();

        sudokuGrid.add(textField1, 2 , 1);





        sudokuGrid.getChildren();
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        board.solveGame();
        this.diffLevel.deleteFields(board);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board.getField(i,j));

            }
            System.out.print("\n");
        }


    }


}
