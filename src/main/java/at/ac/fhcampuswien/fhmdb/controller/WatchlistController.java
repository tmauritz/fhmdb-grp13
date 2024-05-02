package at.ac.fhcampuswien.fhmdb.controller;

import at.ac.fhcampuswien.fhmdb.database.DatabaseManager;
import at.ac.fhcampuswien.fhmdb.exceptions.DatabaseException;
import at.ac.fhcampuswien.fhmdb.ui.UiLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WatchlistController implements Initializable {

    public void showMainMenu() throws IOException{
        UiLoader.showMainMenu();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
/*        try {
            DatabaseManager.getDatabaseManager();
        } catch (DatabaseException e) {

        }*/
    }
}
