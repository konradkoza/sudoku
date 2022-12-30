package pl.comp.view;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lodz.p.pk.dao.Dao;
import lodz.p.pk.dao.SudokuBoardDaoFactory;
import lodz.p.pk.sudoku.BacktrackingSudokuSolver;
import lodz.p.pk.sudoku.SudokuBoard;

public class DifficultySelectController {

        private DifficultyLevel chosenLevel;

        private final   Locale locale_pl = new Locale.Builder()
                .setLanguage("pl")
                .build();

        private final   Locale locale_en = new Locale.Builder()
                .setLanguage("en")
                .build();



        ResourceBundle bundle = ResourceBundle.getBundle("pl.comp.view.LangBundle");

        @FXML
        private Button selectEnButton;

        @FXML
        private Button selectPlButton;

        @FXML
        private TextField fileText;

        @FXML
        private CheckBox checkBoxEasy;

        @FXML
        private CheckBox checkBoxHard;

        @FXML
        private CheckBox checkBoxMedium;

        @FXML
        private void handleEasyBox() {
            if (checkBoxEasy.isSelected()) {
                checkBoxHard.setSelected(false);
                checkBoxMedium.setSelected(false);
                chosenLevel = DifficultyLevel.EASY;
            }
        }

        @FXML
        private void handleMediumBox() {
            if (checkBoxMedium.isSelected()) {
                checkBoxHard.setSelected(false);
                checkBoxEasy.setSelected(false);
                chosenLevel = DifficultyLevel.MEDIUM;
            }
        }

        @FXML
        private void handleHardBox() {
            if (checkBoxHard.isSelected()) {
                checkBoxEasy.setSelected(false);
                checkBoxMedium.setSelected(false);
                chosenLevel = DifficultyLevel.HARD;
            }
        }

    @FXML
    private void startGame(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SudokuBoard.fxml"), bundle);

        Parent root = loader.load();
        SudokuBoardController sudokuBoardController = loader.getController();
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        board.solveGame();
        chosenLevel.deleteFields(board);
        sudokuBoardController.initTextFields(board);

        StageManager.showStage(root);
    }


    @FXML
    private void readFromFile(ActionEvent event) throws IOException{
        String fileName = fileText.getText();
        SudokuBoard board;

        try(Dao<SudokuBoard> dao = SudokuBoardDaoFactory.getFileDao(fileName)){
            board = dao.read();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if(board != null){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SudokuBoard.fxml"), bundle);
            Parent root = loader.load();
            SudokuBoardController sudokuBoardController = loader.getController();
            sudokuBoardController.initTextFields(board);
            StageManager.showStage(root);
        }
    }

    @FXML
    private void selectPl(ActionEvent event) throws IOException {
        bundle = ResourceBundle.getBundle("pl.comp.view.LangBundle",locale_pl);
        Locale.setDefault(locale_pl);

        Parent root = FXMLLoader.load(getClass().getResource("DifficultyLevel.fxml"), bundle);
        StageManager.showStage(root);
    }

    @FXML
    private void selectEn(ActionEvent event) throws IOException {
        bundle = ResourceBundle.getBundle("pl.comp.view.LangBundle",locale_en);
        Locale.setDefault(locale_en);
        Parent root = FXMLLoader.load(getClass().getResource("DifficultyLevel.fxml"), bundle);
        StageManager.showStage(root);

    }

}
