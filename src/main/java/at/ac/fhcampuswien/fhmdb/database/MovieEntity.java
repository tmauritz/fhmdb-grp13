package at.ac.fhcampuswien.fhmdb.database;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "movie")
public class MovieEntity {
    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String apiId;

    @DatabaseField
    private String title;

    @DatabaseField
    private String description;

    @DatabaseField
    private String genres;

    @DatabaseField
    private int releaseYear;

    @DatabaseField
    private String imgUrl;

    @DatabaseField
    private int lengthInMinutes;

    public MovieEntity(){}

    public MovieEntity(String apiId, String title, String description, String genres, int releaseYear, String imgUrl, int lengthInMinutes) {
        this.apiId = apiId;
        this.title = title;
        this.description = description;
        this.genres = genres;
        this.releaseYear = releaseYear;
        this.imgUrl = imgUrl;
        this.lengthInMinutes = lengthInMinutes;
    }

    public MovieEntity(Movie movie){
       this.apiId = movie.getId();
       this.title = movie.getTitle();
       this.description = movie.getDescription();
       StringBuilder genresBuilder = new StringBuilder();
       movie.getGenres().forEach(genre -> genresBuilder.append(genre).append(", "));
       genresBuilder.replace(genresBuilder.length() - 2, genresBuilder.length(), "");
       this.genres = genresBuilder.toString();
       this.releaseYear = movie.getReleaseYear();
       this.imgUrl = movie.getUrl();
       this.lengthInMinutes = movie.getLengthInMinutes();
    }
}
