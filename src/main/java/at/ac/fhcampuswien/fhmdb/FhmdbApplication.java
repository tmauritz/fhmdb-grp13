package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.ui.UiLoader;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;

public class FhmdbApplication extends Application {
    @Override
    public void start(Stage stage) {
        UiLoader.setMainStage(stage);
        try {
            UiLoader.showMainMenu();
        } catch (IOException e) {
            UiLoader.showError("Critical Error", "Application was closed.", e);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}