package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Job;
import at.ac.fhcampuswien.fhmdb.models.Movie;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MovieAPI {

    private final OkHttpClient client = new OkHttpClient();

    /**
     * loads all Movies
     * @return a List of all Movies
     */
    public List<Movie> loadMovies() throws Exception{
        //GET /movies

        Request request = new Request.Builder()
                .url("https://jobs.postmanatwork.com/jobs")
                .header("User-Agent", "MovieAPI.java")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            if(response.body()!=null) {
                String json = response.body().string();
                System.out.println(json);
                Gson gson = new Gson();

                Type type = new TypeToken<List<Job>>() {
                }.getType();

                List<Job> jobs = gson.fromJson(json, type);
                System.out.println("debuuggg");
            }
            // Deserialization
            // Note: For older Gson versions it is necessary to use `collectionType.getType()` as argument below,
            // this is however not type-safe and care must be taken to specify the correct type for the local variable
            //Job[] jobs = gson.fromJson(response.body().string(), Job[].class);

        }


        return new ArrayList<>();
    }

    public List<Movie> loadMovies(String query, Genre genre, int releaseYear, double rating){
        // GET /movies?query=""&genre=""&releaseYear=&rating=

        return new ArrayList<>();
    }

}
