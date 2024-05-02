package at.ac.fhcampuswien.fhmdb.database;

import at.ac.fhcampuswien.fhmdb.exceptions.DatabaseException;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;

import java.sql.SQLException;
import java.util.List;

public class WatchlistRepository {

    private final Dao<WatchlistMovieEntity, Integer> watchlistDao;

    public WatchlistRepository() throws DatabaseException {
        watchlistDao = DatabaseManager.getDatabaseManager().getWatchlistDao();
    }

    /**
     * Adds single entity to watchlist.
     * @param movie WatchlistMovieEntity.
     * @throws SQLException Throws exception when encountering issues with the database.
     */
    public void addToWatchlist(WatchlistMovieEntity movie) throws DatabaseException {
        try {
            watchlistDao.create(movie);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public List<WatchlistMovieEntity> getWatchlist() throws DatabaseException {
        try {
            return watchlistDao.queryForAll();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    /**
     * Removes single entity from watchlist, based on the movie's ID.
     * @param apiId ID of selected movie.
     * @return The number of deleted movies from database.
     * @throws SQLException Throws exception when encountering issues with the database.
     */
    public int removeFromWatchlist(String apiId) throws DatabaseException {
        DeleteBuilder<WatchlistMovieEntity, Integer> deleteBuilder = watchlistDao.deleteBuilder();
        try {
            deleteBuilder.where().ge("apiId", apiId);
            return deleteBuilder.delete();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }
}
