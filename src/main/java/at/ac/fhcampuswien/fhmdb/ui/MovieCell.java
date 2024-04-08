package at.ac.fhcampuswien.fhmdb.ui;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.List;

public class MovieCell extends ListCell<Movie> {
    private final Label title = new Label();
    private final Label detail = new Label();
    private final VBox layout = new VBox(title, detail);

    /**
     * Handles visual representation of a movie
     * @param movie The new item for the cell.
     * @param empty whether or not this cell represents data from the list. If it
     *        is empty, then it does not represent any domain data, but is a cell
     *        being used to render an "empty" row.
     */
    @Override
    protected void updateItem(Movie movie, boolean empty) {
        super.updateItem(movie, empty);

        if (empty || movie == null) {
            setText(null);
            setGraphic(null);
        } else {
            this.getStyleClass().add("movie-cell");
            title.setText(movie.getTitle());
            StringBuilder detailText = new StringBuilder(
                    movie.getDescription() != null
                    ? movie.getDescription()
                    : "No description available"
            );
            detailText.append(System.lineSeparator()).append(System.lineSeparator());
            detailText.append("Release: ").append(movie.getReleaseYear()).append("  |  ★ ").append(movie.getRating()).append(System.lineSeparator());
            List<Genre> genres = movie.getGenres();
            for (int i = 1; i <= genres.size(); i++){
                detailText.append(genres.get(i-1).toString().replaceAll("_", " "));
                if(i != genres.size()) detailText.append(", ");
            }
            detail.setText(detailText.toString());


            // color scheme
            title.getStyleClass().add("text-yellow");
            detail.getStyleClass().add("text-white");
            layout.setBackground(new Background(new BackgroundFill(Color.web("#454545"), null, null)));

            // layout
            title.fontProperty().set(Font.font(20));
            detail.setMaxWidth(this.getScene().getWidth() - 30);
            detail.setWrapText(true);
            layout.setPadding(new Insets(10));
            layout.spacingProperty().set(10);
            layout.alignmentProperty().set(javafx.geometry.Pos.CENTER_LEFT);
            setGraphic(layout);
        }
    }
}

