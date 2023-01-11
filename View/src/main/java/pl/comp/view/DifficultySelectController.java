package pl.comp.view;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import lodz.p.pk.dao.Dao;
import lodz.p.pk.dao.SudokuBoardDaoFactory;
import lodz.p.pk.sudoku.BacktrackingSudokuSolver;
import lodz.p.pk.sudoku.SudokuBoard;

public class DifficultySelectController {

        private DifficultyLevel chosenLevel;

        private final   Locale localePL = new Locale.Builder()
                .setLanguage("pl")
                .build();

        private final   Locale localeEN = new Locale.Builder()
                .setLanguage("en")
                .build();



        ResourceBundle bundle = ResourceBundle.getBundle("pl.comp.view.LangBundle");

        ResourceBundle authors = ResourceBundle.getBundle("pl.comp.view.Authors");

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
        private Button authorsButton;

        @FXML
        void displayAuthors(ActionEvent event) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(bundle.getString("authors"));
            alert.setContentText(authors.getObject("1 ") + "\n" + authors.getObject("2 "));
            alert.show();
        }

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
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        board.solveGame();
        chosenLevel.deleteFields(board);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SudokuBoard.fxml"), bundle);
        Parent root = loader.load();
        SudokuBoardController sudokuBoardController = loader.getController();
        sudokuBoardController.initTextFields(board);
        StageManager.showStage(root);
    }


    @FXML
    private void readFromFile(ActionEvent event) throws IOException {
        String fileName = fileText.getText();
        SudokuBoard board;

        try (Dao<SudokuBoard> dao = SudokuBoardDaoFactory.getFileDao(fileName)) {
            board = dao.read();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (board != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SudokuBoard.fxml"), bundle);
            Parent root = loader.load();
            SudokuBoardController sudokuBoardController = loader.getController();
            sudokuBoardController.initTextFields(board);
            StageManager.showStage(root);
        }
    }

    @FXML
    private void selectPl(ActionEvent event) throws IOException {
        bundle = ResourceBundle.getBundle("pl.comp.view.LangBundle", localePL);
        Locale.setDefault(localePL);

        Parent root = FXMLLoader.load(getClass().getResource("DifficultyLevel.fxml"), bundle);
        StageManager.showStage(root);
    }

    @FXML
    private void selectEn(ActionEvent event) throws IOException {
        bundle = ResourceBundle.getBundle("pl.comp.view.LangBundle", localeEN);
        Locale.setDefault(localeEN);
        Parent root = FXMLLoader.load(getClass().getResource("DifficultyLevel.fxml"), bundle);
        StageManager.showStage(root);

    }

}
