package pl.comp.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

public class DifficultySelectController {

    @FXML
    private Button accesptButton;

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
        }
    }

    @FXML
    private void handleMediumBox() {
        if (checkBoxMedium.isSelected()) {
            checkBoxHard.setSelected(false);
            checkBoxEasy.setSelected(false);
        }
    }

    @FXML
    private void handleHardBox() {
        if (checkBoxHard.isSelected()) {
            checkBoxEasy.setSelected(false);
            checkBoxMedium.setSelected(false);
        }
    }

    @FXML
    void startGame(ActionEvent event) {

    }



}
