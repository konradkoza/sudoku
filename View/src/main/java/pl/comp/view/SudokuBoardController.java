package pl.comp.view;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import lodz.p.pk.sudoku.BacktrackingSudokuSolver;
import lodz.p.pk.sudoku.SudokuBoard;

public class SudokuBoardController {

    @FXML
    private Button showButton;

    @FXML
    private GridPane sudokuGrid;

    private TextField[][] sudokuNumbers = new TextField[9][9];

    private BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();

    private SudokuBoard board = new SudokuBoard(solver);

    private DifficultyLevel diffLevel = DifficultyLevel.MEDIUM;

    public void setDiffLevel(DifficultyLevel diffLevel) {
        this.diffLevel = diffLevel;
    }


    public void printBoard(){
        solver.fillBoard(board);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board.getField(i,j) + "  ");
            }
            System.out.print("\n");
        }
        updateTextFields();
    }


    private void updateTextFields(){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(board.getField(i, j) != 0) {
                    sudokuNumbers[i][j].setText(String.valueOf(board.getField(i, j)));
                } else {
                    sudokuNumbers[i][j].setText("");
                }
            }
        }
    }

    private void initTextFields() {
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
                int finalJ = j;
                int finalI = i;
                newNumber.textProperty().addListener((observable, oldValue, newValue) -> {
                    if(!(newValue.matches("[1-9]") || newValue.equals(""))){
                        newNumber.setText(oldValue);
                    }
                    if(newValue.matches("[1-9]")){
                        board.setField(finalI, finalJ, Integer.parseInt(newValue));}
                });
                if(newNumber.getText().matches("[1-9]")) {
                    newNumber.setFont(Font.font("System Regular", FontWeight.LIGHT, 12));
                    Paint bgColor = Paint.valueOf("EEEEEE");
                    newNumber.setStyle("-fx-control-inner-background: #"+bgColor.toString().substring(2));
                    newNumber.setEditable(false);
                }
                sudokuNumbers[i][j] = newNumber;
                sudokuGrid.add(sudokuNumbers[i][j], i, j);

            }
        }
    }

    public void initialize() {


        board.solveGame();
        diffLevel.deleteFields(board);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board.getField(i,j));
            }
            System.out.print("\n");
        }

        initTextFields();
    }
}
