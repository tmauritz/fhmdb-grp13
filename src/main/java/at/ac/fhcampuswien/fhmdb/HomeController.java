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
    public JFXComboBox<String> releaseYearComboBox;

    @FXML
    public JFXComboBox<String> ratingComboBox;

    @FXML
    public JFXButton sortBtn;

    @FXML
    public JFXButton resetBtn;

    private final List<Movie> allMovies = Movie.initializeMovies();
    private Map<String, Genre> genreMap;

    private final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes
    private MovieAPI api;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        api = new MovieAPI();
        List<Movie> loadedMovies = api.loadMovies();
        observableMovies.addAll(loadedMovies);         // add data to observable list
        updateFilterOptions(loadedMovies);

        // initialize UI stuff
        sortMovies(observableMovies,true);
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data

        //add genre filter items
        genreComboBox.setPromptText("Filter by Genre");
        releaseYearComboBox.setPromptText("Filter by Release Year");
        ratingComboBox.setPromptText("Filter by Rating");
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

        ObservableList<String> ratingObservableList = FXCollections.observableArrayList();
        for (int i = 1; i <= 10; i++) {
            ratingObservableList.add(String.valueOf(i));
        }
        ratingComboBox.setItems(ratingObservableList);

        // Sort button example:
        sortBtn.setPrefWidth(80);
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

        searchBtn.setOnAction(actionEvent -> setupForFilter());
        searchField.setOnAction(actionEvent -> setupForFilter());
        resetBtn.setOnAction(actionEvent -> resetFilter());
    }

    private void setupForFilter() {
        observableMovies.clear();
        Genre searchGenre = genreMap.getOrDefault(genreComboBox.getValue(), Genre.ALL);
        double rating = Double.parseDouble(ratingComboBox.getValue() == null ? "0" : ratingComboBox.getValue());
        int releaseYear = Integer.parseInt(releaseYearComboBox.getValue() == null ? "-1" : releaseYearComboBox.getValue());
        observableMovies.addAll(filterMovies(searchField.getText(), searchGenre, releaseYear, rating));
        sortMovies(observableMovies, sortBtn.getText().equals("Sort (asc)"));
        resetBtn.setDisable(searchField.getText().isBlank() && searchGenre.equals(Genre.ALL) && releaseYearComboBox.getValue() == null && ratingComboBox.getValue() == null);
    }

    public void updateFilterOptions(List<Movie> movies) {
        ObservableList<String> releaseYear = FXCollections.observableArrayList();
        for (Movie movie: movies) {
            if (!releaseYear.contains(String.valueOf(movie.getReleaseYear()))) releaseYear.add(String.valueOf(movie.getReleaseYear()));
        }
        Collections.sort(releaseYear);
        releaseYearComboBox.setItems(releaseYear);
    }

    public void resetFilter(){
        searchField.clear();
        genreComboBox.getSelectionModel().clearSelection();
        ratingComboBox.getSelectionModel().clearSelection();
        releaseYearComboBox.getSelectionModel().clearSelection();
        setupForFilter();
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

    public List<Movie> filterMovies(String query, Genre genre, int releaseYear, double rating) {
        List<Movie> filteredMovies = api.loadMovies(query, genre, releaseYear, rating);
        updateFilterOptions(filteredMovies);
        return filteredMovies;
    }

    public void sortMovies(List<Movie> movies, boolean ascending) {
        if (ascending) Collections.sort(movies);
        else movies.sort(Comparator.reverseOrder());
    }

    public String getMostPopularActor(List<Movie> movies) {

        return "";
    }

    public int getLongestMovieTitle(List<Movie> movies) {
        return 0;
    }

    public long countMoviesFrom(List<Movie> movies, String director){
        return 0;
    }

    public List<Movie> getMoviesBetweenYears(List<Movie> movies, int startYear, int endYear) {
        return new ArrayList<>();
    }
}