package at.ac.fhcampuswien.fhmdb.ui;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.jfoenix.controls.JFXButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.List;

public class MovieCell extends ListCell<Movie> {
    private final Label title = new Label();
    private final Label detail = new Label();
    JFXButton watchlistBtn = new JFXButton();
    private final VBox movieInfo = new VBox(title, detail);
    private final VBox buttons = new VBox(watchlistBtn);
    private final HBox layout = new HBox(movieInfo, buttons);

    public MovieCell(ClickEventHandler addToWatchlistClicked) {
        super();
        watchlistBtn.setText("Watchlist");
        watchlistBtn.setOnMouseClicked(mouseEvent -> addToWatchlistClicked.onClick(getItem()));
    }

    /**
     * Handles visual representation of a movie
     *
     * @param movie The new item for the cell.
     * @param empty whether or not this cell represents data from the list. If it
     *              is empty, then it does not represent any domain data, but is a cell
     *              being used to render an "empty" row.
     */
    @Override
    protected void updateItem(Movie movie, boolean empty){
        super.updateItem(movie, empty);

        if(empty || movie == null){
            setText(null);
            setGraphic(null);
        } else{
            this.getStyleClass().add("movie-cell");
            title.setText(movie.getTitle());
            StringBuilder detailText = new StringBuilder(movie.getDescription() != null ? movie.getDescription() : "No description available");
            detailText.append(System.lineSeparator()).append(System.lineSeparator());
            detailText.append("Release: ").append(movie.getReleaseYear()).append("  |  ★ ").append(movie.getRating()).append(System.lineSeparator());
            List<Genre> genres = movie.getGenres();
            for(int i = 1; i <= genres.size(); i++){
                detailText.append(genres.get(i - 1).toString().replaceAll("_", " "));
                if(i != genres.size()) detailText.append(", ");
            }
            detail.setText(detailText.toString());


            // color scheme
            title.getStyleClass().add("text-yellow");
            detail.getStyleClass().add("text-white");
            watchlistBtn.getStyleClass().add("background-yellow");
            layout.setBackground(new Background(new BackgroundFill(Color.web("#454545"), null, null)));

            // layout
            title.fontProperty().set(Font.font(20));
            detail.setMaxWidth(this.getScene().getWidth() - 200);
            detail.setWrapText(true);
            layout.setPadding(new Insets(10));
            movieInfo.spacingProperty().set(10);
            movieInfo.alignmentProperty().set(javafx.geometry.Pos.CENTER_LEFT);
            movieInfo.setPrefWidth(this.getScene().getWidth() - 155);
            watchlistBtn.setPrefWidth(90);
            buttons.setMaxWidth(150);
            buttons.setAlignment(Pos.TOP_RIGHT);
            setGraphic(layout);
        }
    }
}

