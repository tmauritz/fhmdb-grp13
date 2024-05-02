package at.ac.fhcampuswien.fhmdb.api;

import at.ac.fhcampuswien.fhmdb.exceptions.MovieApiException;
import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.google.gson.Gson;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;

public class MovieAPI {
    private final String apiUrl = "https://prog2.fh-campuswien.ac.at/movies";

    private final OkHttpClient client = new OkHttpClient();

    /**
     * loads all Movies
     *
     * @return all Movies
     */
    public List<Movie> loadMovies() throws MovieApiException {
        Request request = new Request.Builder()
                .url(apiUrl)
                .header("User-Agent", "MovieAPI.java")
                .build();

        return getMovies(request);
    }

    /**
     * loads Movies matching search criteria
     *
     * @param query       text query (searching title and description)
     * @param genre       movie Genre
     * @param releaseYear Release year of the movie
     * @param rating      movie rating
     * @return Movies matching the search criteria
     */
    public List<Movie> loadMovies(String query, Genre genre, int releaseYear, double rating) throws MovieApiException {
        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(apiUrl)).newBuilder();
        urlBuilder
                .addQueryParameter("query", query)
                .addQueryParameter("ratingFrom", Double.toString(rating));

        if (releaseYear != -1) urlBuilder.addQueryParameter("releaseYear", Integer.toString(releaseYear));
        if (genre != Genre.ALL) urlBuilder.addQueryParameter("genre", genre.toString());

        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .header("User-Agent", "MovieAPI.java")
                .build();

        return getMovies(request);
    }

    /**
     * Catches response if request invalid
     * Loads json from server and translates it into Java objects
     *
     * @param request http request to server
     * @return Movie list
     */

    @NotNull
    private List<Movie> getMovies(Request request) throws MovieApiException {
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new MovieApiException();

            if (response.body() != null) {
                Gson gson = new Gson();
                return Arrays.asList(gson.fromJson(response.body().string(), Movie[].class));
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
        return new ArrayList<>();
    }


}
