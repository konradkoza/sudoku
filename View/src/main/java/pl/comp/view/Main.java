package pl.comp.view;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            StageManager.setStage(primaryStage);
            Parent root = FXMLLoader.load(getClass().getResource("DifficultyLevel.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Sudoku Game");
            StageManager.setScene(scene);
            StageManager.showStage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
