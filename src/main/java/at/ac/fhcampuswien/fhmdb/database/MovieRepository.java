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

    public List<MovieEntity> getAllMovies() throws SQLException {
        return movieDao.queryForAll();
    }

    public int addAllMovies(List<Movie> movies) throws SQLException {
        List<MovieEntity> entities = new LinkedList<>();
        movies.forEach(movie -> entities.add(new MovieEntity(movie)));
        return movieDao.create(entities);
    }

    public int removeAll() throws SQLException {
        return movieDao.delete(getAllMovies());
    }

    public MovieEntity getMovie(String apiId){
        //TODO: ask if this is needed
        return null;
    }

    public void removeFromMovies(Movie movie) throws SQLException {
        movieDao.delete(movieToEntity(movie));
    }

    private MovieEntity movieToEntity(Movie movie){
        return new MovieEntity(movie);
    }

}
