package at.ac.fhcampuswien.fhmdb.database;

import at.ac.fhcampuswien.fhmdb.exceptions.DatabaseException;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

public class WatchlistRepository {

    private static WatchlistRepository watchlistRepository;
    private final Dao<WatchlistMovieEntity, Integer> watchlistDao;

    private WatchlistRepository() throws DatabaseException {
        watchlistDao = DatabaseManager.getDatabaseManager().getWatchlistDao();
    }

    public static WatchlistRepository getWatchlistRepository() throws DatabaseException {
        if (watchlistRepository == null) watchlistRepository = new WatchlistRepository();
        return watchlistRepository;
    }

    /**
     * Adds single entity to watchlist.
     * @param movie WatchlistMovieEntity.
     * @throws DatabaseException Throws exception when encountering issues with the database.
     */
    public void addToWatchlist(WatchlistMovieEntity movie) throws DatabaseException {
        try {
            watchlistDao.create(movie);
        } catch (SQLException e) {
            throw new DatabaseException("Unable to add Element to Watchlist", e);
        }
    }

    public boolean isOnWatchList(Movie movie) throws DatabaseException {
        QueryBuilder<WatchlistMovieEntity, Integer> whereQuery = watchlistDao.queryBuilder();
        List<WatchlistMovieEntity> result;
        try {
            whereQuery.where().eq("apiID", movie.getId());
            result = whereQuery.query();
        } catch (SQLException e) {
            throw new DatabaseException();
        }
        return result != null && !result.isEmpty();
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
     * @throws DatabaseException Throws exception when encountering issues with the database.
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
