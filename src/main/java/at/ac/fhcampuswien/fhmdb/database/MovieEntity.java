package at.ac.fhcampuswien.fhmdb.database;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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

    @DatabaseField
    private double rating;

    public MovieEntity(){}

    public MovieEntity(String apiId, String title, String description, String genres, int releaseYear, String imgUrl, int lengthInMinutes, double rating) {
        this.apiId = apiId;
        this.title = title;
        this.description = description;
        this.genres = genres;
        this.releaseYear = releaseYear;
        this.imgUrl = imgUrl;
        this.lengthInMinutes = lengthInMinutes;
        this.rating = rating;
    }

    public MovieEntity(Movie movie){
       this.apiId = movie.getId();
       this.title = movie.getTitle();
       this.description = movie.getDescription();
       this.genres = genresToStringList(movie.getGenres());
       this.releaseYear = movie.getReleaseYear();
       this.imgUrl = movie.getUrl();
       this.lengthInMinutes = movie.getLengthInMinutes();
       this.rating = movie.getRating();
    }

    public String getApiId() {
        return apiId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getGenres() {
        return genres;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public int getLengthInMinutes() {
        return lengthInMinutes;
    }

    public double getRating() {
        return rating;
    }

    private String genresToStringList(List<Genre> genres){
        StringBuilder genresBuilder = new StringBuilder();
        genres.forEach(genre -> genresBuilder.append(genre).append(", "));
        genresBuilder.replace(genresBuilder.length() - 2, genresBuilder.length(), "");
        return genresBuilder.toString();
    }

    public static List<MovieEntity> fromMovies(List<Movie> movies){
        List<MovieEntity>entities = new LinkedList<>();
        movies.forEach(movie->entities.add(new MovieEntity(movie)));
        return entities;
    }

    public static List<Movie> toMovies(List<MovieEntity> movieEntities){
        List<Movie> movies = new LinkedList<>();
        movieEntities.forEach(movieEntity -> movies.add(new Movie(movieEntity)));
        return movies;
    }
}
