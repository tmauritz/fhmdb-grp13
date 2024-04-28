package at.ac.fhcampuswien.fhmdb.models;

import at.ac.fhcampuswien.fhmdb.database.MovieEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Movie implements Comparable<Movie>{
    private final String title;
    private final String description;
    private final List<Genre> genres;
    private final int releaseYear;
    private final double rating;
    private final String id;
    private final List<String> mainCast;
    private final int lengthInMinutes;
    private final List<String> directors;
    private final List<String> writers;
    private final String url;

    public Movie(String id, String title, List<Genre> genres, int releaseYear, String description, String url,  int lengthInMinutes, List<String> directors, List<String> writers, List<String> mainCast, double rating) {
        this.title = title;
        this.description = description;
        this.genres = genres;
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.id = id;
        this.mainCast = mainCast;
        this.lengthInMinutes = lengthInMinutes;
        this.directors = directors;
        this.writers = writers;
        this.url = url;
    }

    public Movie(MovieEntity movieEntity){
        this.title = movieEntity.getTitle();
        this.description = movieEntity.getDescription();
        this.releaseYear = movieEntity.getReleaseYear();
        this.rating = movieEntity.getRating();
        this.id = movieEntity.getApiId();
        this.lengthInMinutes = movieEntity.getLengthInMinutes();
        this.url = movieEntity.getImgUrl();
        this.genres = genreStringToList(movieEntity.getGenres());
        this.mainCast = new LinkedList<>();
        this.directors = new LinkedList<>();
        this.writers = new LinkedList<>();
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public double getRating() {
        return rating;
    }

    public String getId() {
        return id;
    }

    public List<String> getMainCast() {
        return mainCast;
    }

    public int getLengthInMinutes() {
        return lengthInMinutes;
    }

    public List<String> getDirectors() {
        return directors;
    }

    public List<String> getWriters() {
        return writers;
    }

    public String getUrl() {
        return url;
    }

    /**
     * Overrides compareTo method to compare titles (for sort methods)
     * @param o the object to be compared.
     * @return Whether the movie title is greater, lower or equal in alphabet
     */
    @Override
    public int compareTo(Movie o){
        return this.title.compareTo(o.getTitle());
    }

    private List<Genre> genreStringToList(String genreString){
        String[] genreArray = genreString.split(", ");
        List<Genre> genreList = new LinkedList<>();

        for(String genre: genreArray){
            genreList.add(Genre.valueOf(genre));
        }

        return genreList;
    }
}
