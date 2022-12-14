package pl.comp.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import lodz.p.pk.sudoku.BacktrackingSudokuSolver;
import lodz.p.pk.sudoku.SudokuBoard;
import lodz.p.pk.sudoku.SudokuField;
import lodz.p.pk.sudoku.SudokuSolver;

public class SudokuBoardController {

    @FXML
    private GridPane sudokuGrid;

    private TextField[][] sudokuNumbers = new TextField[9][9];

    private DifficultyLevel diffLevel = DifficultyLevel.MEDIUM;

    public void setDiffLevel(DifficultyLevel diffLevel) {
        this.diffLevel = diffLevel;
    }

    private void initTextFields(SudokuBoard board) {
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                TextField newNumber = new TextField();
                newNumber.setPrefWidth(60);
                newNumber.setPrefHeight(60);
                newNumber.alignmentProperty().set(Pos.CENTER);
                if(board.getField(i, j) != 0) {
                    newNumber.setText(String.valueOf(board.getField(i, j)));
                } else {
                    newNumber.setText("");
                }
                newNumber.setFont(Font.font("System Regular", FontWeight.BOLD, 12));
                newNumber.textProperty().addListener((observable, oldValue, newValue) -> {
                    if(!(newValue.matches("[1-9]") || newValue.equals(""))){
                        newNumber.setText(oldValue);
                    }
                });
                if(newNumber.getText().matches("[1-9]")) {
                    newNumber.setFont(Font.font("System Regular", FontWeight.LIGHT, 12));
                    Paint bgColor = Paint.valueOf("EEEEEE");
                    newNumber.setStyle("-fx-control-inner-background: #"+bgColor.toString().substring(2));
                    newNumber.setEditable(false);
                }
                sudokuNumbers[i][j] = newNumber;
                sudokuGrid.add(sudokuNumbers[i][j], i, j);
                board.setField(0, 0, 999);
            }
        }
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

        initTextFields(board);
    }
}
