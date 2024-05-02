package at.ac.fhcampuswien.fhmdb.database;

import at.ac.fhcampuswien.fhmdb.exceptions.DatabaseException;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class DatabaseManager {
    public final static String DB_URL = "jdbc:h2:file: ./database/moviecache";
    public final static String username = "root";
    public final static String password = "passwort";

    public static ConnectionSource connectionSource;
    private final Dao<MovieEntity, Integer> movieDao;
    private final Dao<WatchlistMovieEntity, Integer> watchlistDao;
    private static DatabaseManager databaseManager;

    /**
     * Private constructor ensures no instances of class can be made.
     * Creates the necessary daos and sets up the database.
     */
    private DatabaseManager() throws DatabaseException {
            createConnection();
        try {
            movieDao = DaoManager.createDao(connectionSource, MovieEntity.class);
            watchlistDao = DaoManager.createDao(connectionSource, WatchlistMovieEntity.class);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
            createTables();
    }

    /**
     * Singleton pattern. Creates one single instance of the class,
     * ensures no additional ones are made.
     * @return The project's databaseManager.
     */
    public static DatabaseManager getDatabaseManager() throws DatabaseException {
        if (databaseManager == null) databaseManager = new DatabaseManager();
        return databaseManager;
    }

    public Dao<MovieEntity, Integer> getMovieDao() {
        return movieDao;
    }

    public Dao<WatchlistMovieEntity, Integer> getWatchlistDao() {
        return watchlistDao;
    }

    /**
     * Checks if tables for the Entity classes have been made, creates new ones if not.
     * @throws DatabaseException Throws exception when encountering issues with the database.
     */
    private static void createTables() throws DatabaseException {
        try {
            TableUtils.createTableIfNotExists(connectionSource, MovieEntity.class);
            TableUtils.createTableIfNotExists(connectionSource, WatchlistMovieEntity.class);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    /**
     * Sets new connection source with the defined parameters of URL, username, password.
     * @throws DatabaseException Throws exception when encountering issues with the database.
     */
    private static void createConnection() throws DatabaseException {
        try {
            connectionSource = new JdbcConnectionSource(DB_URL, username, password);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

}
