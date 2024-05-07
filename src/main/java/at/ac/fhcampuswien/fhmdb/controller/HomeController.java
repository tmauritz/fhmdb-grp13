package at.ac.fhcampuswien.fhmdb.controller;

import at.ac.fhcampuswien.fhmdb.api.MovieAPI;
import at.ac.fhcampuswien.fhmdb.database.*;
import at.ac.fhcampuswien.fhmdb.exceptions.DatabaseException;
import at.ac.fhcampuswien.fhmdb.exceptions.MovieApiException;
import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.ui.ClickEventHandler;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import at.ac.fhcampuswien.fhmdb.ui.UiLoader;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    private Map<String, Genre> genreMap;

    private final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes
    private MovieAPI api;
    private List<Movie> cachedMovies;

    public final ClickEventHandler onAddToWatchlistClicked = (movie) ->
    {
        try {
            WatchlistRepository.getWatchlistRepository().addToWatchlist(new WatchlistMovieEntity(movie.getId()));
        } catch (DatabaseException e) {
            //check if Movie is on Watchlist
            boolean onWatchlist = false;
            try {
                if(WatchlistRepository.getWatchlistRepository().isOnWatchList(movie)){
                    UiLoader.showInfo("Element already on Watchlist.");
                    onWatchlist = true;
                }else{
                    UiLoader.showError("Watchlist Error", e.getMessage(), e);
                }
            } catch (DatabaseException ex) {
                UiLoader.showError("Error communicating with Database", ex.getMessage(), ex);
            }
            if(!onWatchlist){
                e.printStackTrace();
                UiLoader.showError("Watchlist Error", e.getMessage(), e);
            }
        }
    };

    /**
     * Initializes movie list and ui
     * Add functionality for buttons and filters
     *
     * @param url
     * The location used to resolve relative paths for the root object, or
     * {@code null} if the location is not known.
     *
     * @param resourceBundle
     * The resources used to localize the root object, or {@code null} if
     * the root object was not localized.
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            api = new MovieAPI();
            cachedMovies = api.loadMovies();
            MovieRepository.getMovieRepository().addAllMovies(cachedMovies);
        } catch (MovieApiException e){
            UiLoader.showError("API connection failed","Couldn't establish a connection to the API. Check your internet connection. Switching to offline mode.");
            try {
                cachedMovies = MovieEntity.toMovies(MovieRepository.getMovieRepository().getAllMovies());
            } catch (DatabaseException ex) {
                UiLoader.showError("Cache Error", "Application must be closed.");
                UiLoader.close();
            }
        } catch (DatabaseException e){
            UiLoader.showError("Database connection failed", "Couldn't update cache. Check database connection.");
        }
        observableMovies.addAll(cachedMovies);         // add data to observable list
        updateReleaseYearComboBox(cachedMovies);

        // initialize UI stuff
        sortMovies(observableMovies,true);
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell(onAddToWatchlistClicked)); // use custom cell factory to display data

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
        ratingObservableList.add("All");
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

        genreComboBox.setOnAction(actionEvent -> updateReleaseYearComboBox(filterMovies(cachedMovies,"", genreMap.getOrDefault(genreComboBox.getValue(), Genre.ALL), 0, 0)));
        searchBtn.setOnAction(actionEvent -> {
            executeFilter();
            try {
                cachedMovies = api.loadMovies();
                MovieRepository.getMovieRepository().removeAll();
                MovieRepository.getMovieRepository().addAllMovies(cachedMovies);
            } catch (MovieApiException e) {
                UiLoader.showError("API connection failed","Couldn't establish a connection to the API. Check your internet connection.");
                try {
                    cachedMovies = MovieEntity.toMovies(MovieRepository.getMovieRepository().getAllMovies());
                } catch (DatabaseException ex) {
                    UiLoader.showError("Cache Error", "Application must be closed.");
                    UiLoader.close();
                }
            } catch (DatabaseException e){
                UiLoader.showError("Database connection failed", "Couldn't establish a connection to the database.");
            }
        });
        searchField.setOnAction(actionEvent -> executeFilter());
        resetBtn.setOnAction(actionEvent -> resetFilter());
    }

    /**
     * Clears previous movie list
     * Gets filter values
     * Executes filter request
     * Toggles reset button
     */

    private void executeFilter() {
        observableMovies.clear();

        Genre searchGenre = genreMap.getOrDefault(genreComboBox.getValue(), Genre.ALL);
        double rating = Double.parseDouble(ratingComboBox.getValue() == null || ratingComboBox.getValue().equals("All") ? "0" : ratingComboBox.getValue());
        int releaseYear = Integer.parseInt(releaseYearComboBox.getValue() == null || releaseYearComboBox.getValue().equals("All") ? "-1" : releaseYearComboBox.getValue());

        observableMovies.addAll(filterApiMovies(searchField.getText(), searchGenre, releaseYear, rating));
        sortMovies(observableMovies, sortBtn.getText().equals("Sort (asc)"));
        resetBtn.setDisable(searchField.getText().isBlank() && searchGenre.equals(Genre.ALL) && releaseYearComboBox.getValue() == null && ratingComboBox.getValue() == null);
    }

    /**
     * Updates release year combo box to only display relevant values
     * @param movies current movie list
     */
    public void updateReleaseYearComboBox(List<Movie> movies) {
        ObservableList<String> releaseYear = FXCollections.observableArrayList();
        for (Movie movie: movies) {
            if (!releaseYear.contains(String.valueOf(movie.getReleaseYear()))) releaseYear.add(String.valueOf(movie.getReleaseYear()));
        }
        Collections.sort(releaseYear);
        releaseYear.add(0, "All");
        releaseYearComboBox.setItems(releaseYear);
    }

    /**
     * Resets all filter fields and executes blank search
     */
    public void resetFilter(){
        searchField.clear();
        genreComboBox.getSelectionModel().clearSelection();
        ratingComboBox.getSelectionModel().clearSelection();
        releaseYearComboBox.getSelectionModel().clearSelection();
        executeFilter();
    }

    /**
     * Legacy movie filter!
     * @param movies full movie list
     * @param query search query
     * @param genre selected genre from comboBox
     * @return Filtered movie list
     */
    public List<Movie> filterMovies(List<Movie> movies, String query, Genre genre, int releaseYear, double rating) {
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
        if (releaseYear != 0){
            filteredMovies = filteredMovies.stream()
                    .filter(movie -> movie.getReleaseYear() == releaseYear)
                    .toList();
        }
        if (rating != 0){
            filteredMovies = filteredMovies.stream()
                    .filter(movie -> movie.getRating() >= rating)
                    .toList();
        }
        return filteredMovies;
    }

    /**
     * Filters movie list through api request
     * Updates release year comboBox
     *
     * @param query search query for title
     * @param genre selected genre
     * @param releaseYear selected release year
     * @param rating selected rating (and up)
     * @return Filtered movie list
     */
    public List<Movie> filterApiMovies(String query, Genre genre, int releaseYear, double rating) {
        List<Movie> filteredMovies = null;
        try {
            filteredMovies = api.loadMovies(query, genre, releaseYear, rating);
        } catch (MovieApiException e) {
            try {
                cachedMovies = MovieEntity.toMovies(MovieRepository.getMovieRepository().getAllMovies());
            } catch (DatabaseException ex) {
                UiLoader.showError("Cache Error", "Application must be closed.");
                UiLoader.close();
            }
            filteredMovies = filterMovies(cachedMovies, query, genre, releaseYear, rating);
        }
        updateReleaseYearComboBox(Objects.requireNonNull(filteredMovies));
        return filteredMovies;
    }

    /**
     * Sorts movie list alphabetically
     * @param movies current movie list
     * @param ascending ascending or descending alphabetically
     */
    public void sortMovies(List<Movie> movies, boolean ascending) {
        if (ascending) Collections.sort(movies);
        else movies.sort(Comparator.reverseOrder());
    }

    /**
     * Filters movie list for actors with most movies
     * In case of multiple actors with same amount, choose alphabetically
     *
     * @param movies current movie list
     * @return Name of actor with most movies
     */
    public String getMostPopularActor(List<Movie> movies) {
        return movies.stream()
                .map(Movie::getMainCast)
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .max(Map.Entry.comparingByValue()).map(Map.Entry::getKey)
                .orElse("");
    }

    /**
     * Filters movies to find the title with most characters and returns title length
     * @param movies current movie list
     * @return Title length in int
     */
    public int getLongestMovieTitle(List<Movie> movies) {
        if(movies.isEmpty())  return 0;
        Movie movieLength = movies.stream()
                .max(Comparator.comparingInt(e -> e.getTitle().length()))
                .orElse(null);
        return movieLength.getTitle().length();
    }

    /**
     * Counts movies from specific director
     * @param movies current movie list
     * @param director selected director
     * @return Number of movies from director
     */
    public long countMoviesFrom(List<Movie> movies, String director){
        if(movies.isEmpty()) return 0;
        return movies.stream()
                .filter(e -> e.getDirectors().contains(director))
                .count();
    }

    /**
     * Filters movie list for movies shot between two years
     * If chosen start year is lower than end year, included functionality to swap years
     * @param movies current movie list
     * @param startYear selected start year
     * @param endYear selected end year
     * @return List of movies between selected years
     */
    public List<Movie> getMoviesBetweenYears(List<Movie> movies, int startYear, int endYear) {
        return movies.stream()
                .filter(movie -> movie.getReleaseYear() > Math.min(startYear, endYear) && movie.getReleaseYear() < Math.max(startYear, endYear))
                .toList();
    }

    public void showWatchlist() throws IOException{
        UiLoader.showWatchlist();
    }

}