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
    public JFXListView<Movie> movieListView;

    @FXML
    public JFXComboBox<String> genreComboBox;

    @FXML
    public JFXButton sortBtn;

    private final List<Movie> allMovies = Movie.initializeMovies();
    private Map<String, Genre> genreMap;

    private final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        observableMovies.addAll(allMovies);         // add dummy data to observable list

        // initialize UI stuff
        sortMovies(observableMovies,true);
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data

        //add genre filter items
        genreComboBox.setPromptText("Filter by Genre");
        genreMap = new LinkedHashMap<>();
        //translate genre into capitalized Strings for GUI
        Arrays.stream(Genre.values())
                .forEach(genre -> {
                    StringBuilder result = new StringBuilder();
                    String[] words = genre.toString().toLowerCase().split("_");
                    for (String word : words) {
                        if (!word.isEmpty()) {
                            result.append(Character.toUpperCase(word.charAt(0))); // Capitalize first letter
                            if (word.length() > 1) {
                                result.append(word.substring(1)); // Append rest of the word
                            }
                        }
                        result.append(" ");
                    }
                    genreMap.put(result.toString().trim(), genre);
                });

        ObservableList<String> genreObservableList = FXCollections.observableArrayList(genreMap.keySet());
        genreObservableList.removeAll("all");
        genreComboBox.setItems(genreObservableList);

        // Sort button example:
        sortBtn.setOnAction(actionEvent -> {
            if (sortBtn.getText().equals("Sort (asc)")) {
                // sort observableMovies ascending
                sortMovies(observableMovies, false);
                sortBtn.setText("Sort (desc)");
            } else {
                // sort observableMovies descending
                sortMovies(observableMovies, true);
                sortBtn.setText("Sort (asc)");
            }
        });

        searchBtn.setOnAction(actionEvent -> setupSearch());

        searchField.setOnAction(actionEvent -> setupSearch());
    }

    private void setupSearch() {
        observableMovies.clear();
        Genre searchGenre = genreMap.getOrDefault(genreComboBox.getValue(), Genre.ALL);
        observableMovies.addAll(filterMovies(allMovies, searchField.getText(), searchGenre));
    }

    public List<Movie> filterMovies(List<Movie> movies, String query, Genre genre) {
        List<Movie> filteredMovies = movies;
        query = query.trim().replaceAll("\\s{2,}", " ").toLowerCase();
        if (movies == null || genre == null) throw new IllegalArgumentException();
        if (genre != Genre.ALL) filteredMovies = filteredMovies.stream()
                .filter(movie -> movie.getGenres().contains(genre))
                .toList();
        if (!query.isBlank()) {
            String finalQuery = query;
            filteredMovies = filteredMovies.stream()
                    .filter(movie -> movie.getTitle().toLowerCase().contains(finalQuery) || movie.getDescription().toLowerCase().contains(finalQuery))
                    .toList();
        }
        return filteredMovies;
    }

    public void sortMovies(List<Movie> movies, boolean ascending) {
        if (ascending) Collections.sort(movies);
        else movies.sort(Comparator.reverseOrder());
    }
}