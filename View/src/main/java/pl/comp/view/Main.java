package pl.comp.view;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;


public class Main extends Application {

    Locale locale = new Locale.Builder()
            .setLanguage("en")
            .build();

    ResourceBundle bundle = ResourceBundle.getBundle("pl.comp.view.LangBundle", locale);



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            StageManager.setStage(primaryStage);
            Parent root = FXMLLoader.load(getClass().getResource("DifficultyLevel.fxml"), bundle);
            primaryStage.setTitle("Sudoku Game");
            StageManager.showStage(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
