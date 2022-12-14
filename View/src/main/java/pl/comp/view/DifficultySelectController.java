package pl.comp.view;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

public class DifficultySelectController {

        private DifficultyLevel chosenLevel;


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
    void startGame(ActionEvent event) throws IOException {


        FXMLLoader loader = new FXMLLoader(getClass().getResource("SudokuBoard.fxml"));

        Parent root = loader.load();

        Scene scene = new Scene(root);

        StageManager.setScene(scene);
        StageManager.showStage();

        //        SceneManager sceneManager = new SceneManager();
        //        sceneManager.switchToSudokuBoard(event);
    }




}
