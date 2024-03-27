package pl.comp.view;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.comp.view.exceptions.DaoException;
import pl.comp.view.exceptions.FileException;
import pl.comp.view.exceptions.InsertNumberException;
import pl.comp.view.exceptions.LanguageException;

public class DifficultySelectController {

        private static Logger logger = LoggerFactory.getLogger(DifficultySelectController.class);

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

    public void initialize() {
            fileText.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable,
                                    String oldValue, String newValue) {
                    if (!(newValue.matches("[A-Za-z1-9]+") || newValue.equals(""))) {
                        fileText.setText(oldValue);

                    }
                }
            });
    }

    @FXML
    private void startGame(ActionEvent event) throws FileException, InsertNumberException {
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        board.solveGame();
        chosenLevel.deleteFields(board);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SudokuBoard.fxml"), bundle);
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            logger.info(bundle.getString("GameNotStarted"));
            throw new FileException(e);
        }
        SudokuBoardController sudokuBoardController = loader.getController();
        try {
            sudokuBoardController.initTextFields(board);
        } catch (InsertNumberException e) {
            logger.info(bundle.getString("NumbersNotInserted"));
            throw new InsertNumberException(e);
        }
        StageManager.showStage(root);
    }


    @FXML
    private void readFromFile(ActionEvent event) throws DaoException, FileException,
            InsertNumberException {
        String fileName = fileText.getText();
        SudokuBoard board;
        if (!fileText.getText().isEmpty()) {
            try (Dao<SudokuBoard> dao = SudokuBoardDaoFactory.getJdbcDao(fileName)) {
                board = dao.read();
            } catch (Exception e) {
                logger.info(bundle.getString("FileNotFound"));
                throw new DaoException(e);
            }
            if (board != null) {
                FXMLLoader loader =
                        new FXMLLoader(getClass().getResource("SudokuBoard.fxml"), bundle);
                Parent root = null;
                try {
                    root = loader.load();
                } catch (IOException e) {
                    logger.info(bundle.getString("FileNotLoaded"));
                    throw new FileException(e);
                }
                SudokuBoardController sudokuBoardController = loader.getController();
                try {
                    sudokuBoardController.initTextFields(board);
                } catch (InsertNumberException e) {

                    logger.info(bundle.getString("NumbersNotInserted"));
                    throw new InsertNumberException(e);

                }
                StageManager.showStage(root);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle(bundle.getString("warning"));
            alert.setContentText(bundle.getString("badFileName"));
            alert.show();
        }
    }

    @FXML
    private void selectPl(ActionEvent event) throws LanguageException {
        bundle = ResourceBundle.getBundle("pl.comp.view.LangBundle", localePL);
        Locale.setDefault(localePL);


        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("DifficultyLevel.fxml"), bundle);
        } catch (IOException e) {
            logger.info(bundle.getString("selectLanErr"));
            throw new LanguageException(e);
        }
        StageManager.showStage(root);
    }

    @FXML
    private void selectEn(ActionEvent event) throws LanguageException {
        bundle = ResourceBundle.getBundle("pl.comp.view.LangBundle", localeEN);
        Locale.setDefault(localeEN);
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("DifficultyLevel.fxml"), bundle);
        } catch (IOException e) {
            logger.info(bundle.getString("selectLanErr"));
            throw new LanguageException(e);
        }
        StageManager.showStage(root);

    }

}
