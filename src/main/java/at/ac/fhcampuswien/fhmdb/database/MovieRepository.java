package at.ac.fhcampuswien.fhmdb.database;

import at.ac.fhcampuswien.fhmdb.exceptions.DatabaseException;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class MovieRepository {
    private static MovieRepository movieRepository;
    private final Dao<MovieEntity, Integer> movieDao;

    private MovieRepository() throws DatabaseException {
        try {
            movieDao = DatabaseManager.getDatabaseManager().getMovieDao();
        } catch (DatabaseException e) {
            throw new DatabaseException(e);
        }
    }
    public static MovieRepository getMovieRepository() throws DatabaseException {
        if (movieRepository == null) movieRepository = new MovieRepository();
        return movieRepository;
    }

    /**
     * Reads all movies from database.
     * @return List of movie entities.
     * @throws DatabaseException Throws exception when encountering issues with the database.
     */
    public List<MovieEntity> getAllMovies() throws DatabaseException {
        try {
            return movieDao.queryForAll();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    /**
     * Adds all movies to database.
     * @param movies List of movie objects.
     * @return The number of new entries in the database.
     * @throws DatabaseException Throws exception when encountering issues with the database.
     */
    public int addAllMovies(List<Movie> movies) throws DatabaseException {
        List<MovieEntity> entities = new LinkedList<>();
        movies.forEach(movie -> entities.add(new MovieEntity(movie)));
        try {
            return movieDao.create(entities);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    /**
     * Removes all movie entities from database.
     * @return The number of deleted movies from the database.
     * @throws DatabaseException Throws exception when encountering issues with the database.
     */
    public int removeAll() throws DatabaseException {
        try {
            return movieDao.delete(getAllMovies());
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public MovieEntity getMovie(String apiId){
        //TODO: ask if this is needed
        return null;
    }

    /**
     * Removes single item from database.
     * @param movie Movie object.
     * @throws DatabaseException Throws exception when encountering issues with the database.
     */
    public void removeFromMovies(Movie movie) throws DatabaseException {
        try {
            movieDao.delete(movieToEntity(movie));
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
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
