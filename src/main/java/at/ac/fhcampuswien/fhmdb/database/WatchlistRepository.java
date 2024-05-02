package at.ac.fhcampuswien.fhmdb.database;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;

import java.sql.SQLException;
import java.util.List;

public class WatchlistRepository {

    private Dao<WatchlistMovieEntity, Integer> watchlistDao;

    public WatchlistRepository() {
        watchlistDao = DatabaseManager.getDatabaseManager().getWatchlistDao();
    }

    /**
     * Adds single entity to watchlist.
     * @param movie WatchlistMovieEntity.
     * @throws SQLException Throws exception when encountering issues with the database.
     */
    public void addToWatchlist(WatchlistMovieEntity movie) throws SQLException {
        watchlistDao.create(movie);
    }

    public List<WatchlistMovieEntity> getWatchlist() throws SQLException {
        return watchlistDao.queryForAll();
    }

    /**
     * Removes single entity from watchlist, based on the movie's ID.
     * @param apiId ID of selected movie.
     * @return The number of deleted movies from database.
     * @throws SQLException Throws exception when encountering issues with the database.
     */
    public int removeFromWatchlist(String apiId) throws SQLException {
        DeleteBuilder<WatchlistMovieEntity, Integer> deleteBuilder = watchlistDao.deleteBuilder();
        deleteBuilder.where().ge("apiId", apiId);
        return deleteBuilder.delete();
    }
}
