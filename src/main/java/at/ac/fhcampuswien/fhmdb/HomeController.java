package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.*;

public class HomeController implements Initializable {
    @FXML
    public JFXButton searchBtn;

    @FXML
    public TextField searchField;

    @FXML
    public JFXListView movieListView;

    @FXML
    public JFXComboBox genreComboBox;

    @FXML
    public JFXButton sortBtn;

    public List<Movie> allMovies = Movie.initializeMovies();

    private final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        observableMovies.addAll(allMovies);         // add dummy data to observable list

        // initialize UI stuff
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data

        // TODO add genre filter items with genreComboBox.getItems().addAll(...)
        genreComboBox.setPromptText("Filter by Genre");
        ObservableList<Genre> genreObservableList = FXCollections.observableArrayList(Genre.values());
        genreComboBox.setItems(genreObservableList);

        // TODO add event handlers to buttons and call the regarding methods
        // either set event handlers in the fxml file (onAction) or add them here

        // Sort button example:
        sortBtn.setOnAction(actionEvent -> {
            if(sortBtn.getText().equals("Sort (asc)")) {
                // TODO sort observableMovies ascending
                sortBtn.setText("Sort (desc)");
            } else {
                // TODO sort observableMovies descending
                sortBtn.setText("Sort (asc)");
            }
        });

        searchBtn.setOnAction(actionEvent -> {
            observableMovies.clear();
            observableMovies.addAll(filterMovies(allMovies, searchField.getText(), (Genre) genreComboBox.getValue()));
        });


    }

    public List<Movie> filterMovies(List<Movie> movies, String query, Genre genre){
        List<Movie> filteredMovies = movies;
        query = query.trim().replaceAll("\\s{2,}", " ");
        if(movies == null || query == null || genre == null) throw new IllegalArgumentException();
        if(genre != Genre.ALL) filteredMovies = filteredMovies.stream().filter(movie -> movie.getGenres().contains(genre)).toList();
        if(!query.isBlank()){
            String finalQuery = query;
            filteredMovies = filteredMovies.stream().filter(movie -> movie.getTitle().contains(finalQuery) || movie.getDescription().contains(finalQuery)).toList();
        }
        return filteredMovies;
    }

    public List<Movie> sortMovies(List<Movie> movies,boolean ascending){
        if (ascending) Collections.sort(movies);
        else movies.sort(Comparator.reverseOrder());
        return movies;
    }
}