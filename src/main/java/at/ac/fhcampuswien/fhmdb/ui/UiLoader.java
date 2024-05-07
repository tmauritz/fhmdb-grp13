package at.ac.fhcampuswien.fhmdb.ui;

import at.ac.fhcampuswien.fhmdb.FhmdbApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class UiLoader {
    private static Stage mainStage;
    private static Scene mainMenu, watchlist;

    public static void setMainStage(Stage mainStage){
        UiLoader.mainStage = mainStage;
    }

    public static void setMainStageTitle(String title){
        mainStage.setTitle(title);
    }

    public static void showMainMenu() throws IOException {
        if(mainMenu == null){
            FXMLLoader fxmlLoader = new FXMLLoader(FhmdbApplication.class.getResource("home-view.fxml"));
            mainMenu = new Scene(fxmlLoader.load(), 890, 620);
            mainMenu.getStylesheets().add(Objects.requireNonNull(FhmdbApplication.class.getResource("styles.css")).toExternalForm());
        }
//        mainStage.setTitle("FHMDb");
        mainStage.setScene(mainMenu);
        mainStage.setResizable(false);
        mainStage.show();
    }

    public static void showWatchlist() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(FhmdbApplication.class.getResource("watchlist-view.fxml"));
        watchlist = new Scene(fxmlLoader.load(), 890, 620);
        watchlist.getStylesheets().add(Objects.requireNonNull(FhmdbApplication.class.getResource("styles.css")).toExternalForm());
        mainStage.setTitle("FHMDb");
        mainStage.setScene(watchlist);
        mainStage.setResizable(false);
        mainStage.show();
    }
    public static void showError(String title, String content){
        Alert internetAlert = new Alert(Alert.AlertType.ERROR);
        internetAlert.setTitle(title);
        internetAlert.setHeaderText(null);
        internetAlert.setContentText(content);
        internetAlert.showAndWait();
    }

    public static void showError(String title, String header, Exception e){
        Alert internetAlert = new Alert(Alert.AlertType.ERROR);
        internetAlert.setTitle(title);
        internetAlert.setHeaderText(header);
        internetAlert.setContentText("Details: " + System.lineSeparator() + e.getMessage());
        internetAlert.showAndWait();
    }

    public static void showInfo(String s) {
        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
        infoAlert.setTitle("Info");
        infoAlert.setHeaderText("Info");
        infoAlert.setContentText(s);
        infoAlert.showAndWait();
    }

    public static void close() {
        mainStage.close();
    }
}
