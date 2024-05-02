package at.ac.fhcampuswien.fhmdb.ui;

import at.ac.fhcampuswien.fhmdb.FhmdbApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class UiLoader {
    private static Stage mainStage;
    private static Scene mainMenu, watchlist;

    public static void setMainStage(Stage mainStage){
        UiLoader.mainStage = mainStage;
    }

    public static void showMainMenu() throws IOException{
        if(mainMenu == null){
            FXMLLoader fxmlLoader = new FXMLLoader(FhmdbApplication.class.getResource("home-view.fxml"));
            mainMenu = new Scene(fxmlLoader.load(), 890, 620);
            mainMenu.getStylesheets().add(Objects.requireNonNull(FhmdbApplication.class.getResource("styles.css")).toExternalForm());
        }
        mainStage.setTitle("FHMDb");
        mainStage.setScene(mainMenu);
        mainStage.setResizable(false);
        mainStage.show();
    }

    public static void showWatchlist() throws IOException{
        if (watchlist == null) {
            FXMLLoader fxmlLoader = new FXMLLoader(FhmdbApplication.class.getResource("watchlist-view.fxml"));
            watchlist = new Scene(fxmlLoader.load(), 890, 620);
            watchlist.getStylesheets().add(Objects.requireNonNull(FhmdbApplication.class.getResource("styles.css")).toExternalForm());
        }
        mainStage.setTitle("FHMDb");
        mainStage.setScene(watchlist);
        mainStage.setResizable(false);
        mainStage.show();

    }
}
