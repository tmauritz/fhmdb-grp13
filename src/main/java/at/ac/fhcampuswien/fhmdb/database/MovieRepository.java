package at.ac.fhcampuswien.fhmdb.database;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class MovieRepository {
    private Dao<MovieEntity, Integer> movieDao;

    public MovieRepository() {
        movieDao = DatabaseManager.getDatabaseManager().getMovieDao();
    }

    /**
     * Reads all movies from database.
     * @return List of movie entities.
     * @throws SQLException Throws exception when encountering issues with the database.
     */
    public List<MovieEntity> getAllMovies() throws SQLException {
        return movieDao.queryForAll();
    }

    /**
     * Adds all movies to database.
     * @param movies List of movie objects.
     * @return The number of new entries in the database.
     * @throws SQLException Throws exception when encountering issues with the database.
     */
    public int addAllMovies(List<Movie> movies) throws SQLException {
        List<MovieEntity> entities = new LinkedList<>();
        movies.forEach(movie -> entities.add(new MovieEntity(movie)));
        return movieDao.create(entities);
    }

    /**
     * Removes all movie entities from database.
     * @return The number of deleted movies from the database.
     * @throws SQLException Throws exception when encountering issues with the database.
     */
    public int removeAll() throws SQLException {
        return movieDao.delete(getAllMovies());
    }

    public MovieEntity getMovie(String apiId){
        //TODO: ask if this is needed
        return null;
    }

    /**
     * Removes single item from database.
     * @param movie Movie object.
     * @throws SQLException Throws exception when encountering issues with the database.
     */
    public void removeFromMovies(Movie movie) throws SQLException {
        movieDao.delete(movieToEntity(movie));
    }

    /**
     * Transforms single movie object into movieEntity.
     * @param movie Movie object.
     * @return New movie entity.
     */
    private MovieEntity movieToEntity(Movie movie){
        return new MovieEntity(movie);
    }

}
