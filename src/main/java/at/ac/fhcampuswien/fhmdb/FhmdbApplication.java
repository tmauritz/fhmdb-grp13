package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.database.DatabaseManager;
import at.ac.fhcampuswien.fhmdb.exceptions.DatabaseException;
import at.ac.fhcampuswien.fhmdb.ui.UiLoader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.Objects;

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