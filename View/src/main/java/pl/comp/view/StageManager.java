package pl.comp.view;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class StageManager {

    private static Stage stage;


    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        StageManager.stage = stage;
    }



    public static void showStage(Parent root) {
        Scene scene = new Scene(root);
        stage.setScene(scene);
        StageManager.stage.show();
    }

}
