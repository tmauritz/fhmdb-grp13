package at.ac.fhcampuswien.fhmdb.controller;

import at.ac.fhcampuswien.fhmdb.database.MovieRepository;
import at.ac.fhcampuswien.fhmdb.database.WatchlistMovieEntity;
import at.ac.fhcampuswien.fhmdb.database.WatchlistRepository;
import at.ac.fhcampuswien.fhmdb.exceptions.DatabaseException;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.ui.ClickEventHandler;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import at.ac.fhcampuswien.fhmdb.ui.UiLoader;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class WatchlistController implements Initializable {

    @FXML
    public JFXListView<Movie> watchlistListView;
    private ObservableList<Movie> observableWatchlistMovies = FXCollections.observableArrayList();

    public final ClickEventHandler removeFromWatchlist = (movie) -> {
        try {
            WatchlistRepository.getWatchlistRepository().removeFromWatchlist(movie.getId());
            observableWatchlistMovies.remove(movie);
        } catch (DatabaseException e) {
            UiLoader.databaseError();
        }
    };

    public void showMainMenu() throws IOException{
        UiLoader.showMainMenu();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<WatchlistMovieEntity> watchlistEntities = null;
        try {
            watchlistEntities = WatchlistRepository.getWatchlistRepository().getWatchlist();
        } catch (DatabaseException e) {
            e.printStackTrace();
            UiLoader.databaseError();
        }
        if(watchlistEntities != null){
            watchlistEntities.forEach((entity)->{
                try {
                    Movie movie = MovieRepository.getMovieRepository().getMovie(entity.getApiId());
                    observableWatchlistMovies.add(movie);
                } catch (DatabaseException e) {
                    e.printStackTrace();
                    UiLoader.databaseError();
                }
            });
        }
        watchlistListView.setCellFactory(watchListListView -> new MovieCell(removeFromWatchlist)); // use custom cell factory to display data
        watchlistListView.setItems(observableWatchlistMovies);   // set data of observable list to list view
    }


}
