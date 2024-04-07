package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Job;
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

    private final OkHttpClient client = new OkHttpClient();

    /**
     * loads all Movies
     *
     * @return all Movies
     */
    public List<Movie> loadMovies() {
        Request request = new Request.Builder()
                .url("https://jobs.postmanatwork.com/jobs")
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
    public List<Movie> loadMovies(String query, Genre genre, int releaseYear, double rating) {
        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse("https://jobs.postmanatwork.com/jobs")).newBuilder();
        urlBuilder/*
                .addQueryParameter("query", query)
                .addQueryParameter("genre", genre.toString())
                .addQueryParameter("releaseYear", Integer.toString(releaseYear))
                .addQueryParameter("rating", Double.toString(rating))
                */
                .addQueryParameter("q", query);

        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .header("User-Agent", "MovieAPI.java")
                .build();

        return getMovies(request);
    }

    @NotNull
    private List<Movie> getMovies(Request request) {
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            if (response.body() != null) {
                String json = response.body().string();
                System.out.println(json);
                Gson gson = new Gson();

                List<Job> jobs = Arrays.asList(gson.fromJson(json, Job[].class));
                System.out.println("debuuggg");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }

        return new ArrayList<>();
    }


}
