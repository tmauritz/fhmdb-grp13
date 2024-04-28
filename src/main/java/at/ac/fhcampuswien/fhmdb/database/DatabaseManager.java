package at.ac.fhcampuswien.fhmdb.database;

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
    private Dao<MovieEntity, Integer> movieDao;
    private Dao<WatchlistMovieEntity, Integer> watchlistDao;
    private static DatabaseManager databaseManager;

    private DatabaseManager() {
        //TODO: Move to correct spot
        try {
            createConnection();
            movieDao = DaoManager.createDao(connectionSource, MovieEntity.class);
            watchlistDao = DaoManager.createDao(connectionSource, WatchlistMovieEntity.class);
            createTables();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    /*public void testDatabase() throws SQLException {
       MovieEntity movie = new MovieEntity("a00b56aa-0eaf-4332-a02d-736910950128", "The Goodfather","The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.", "Drama", 1972, "https://m.media-amazon.com/images/M/MV5BM2MyNjYxNmUtYTAwNi00MTYxLWJmNWYtYzZlODY3ZTk3OTFlXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_.jpg",175);
       movieDao.create(movie);
    }*/

    public static DatabaseManager getDatabaseManager() {
        if (databaseManager == null) databaseManager = new DatabaseManager();
        return databaseManager;
    }

    public Dao<MovieEntity, Integer> getMovieDao() {
        return movieDao;
    }

    public Dao<WatchlistMovieEntity, Integer> getWatchlistDao() {
        return watchlistDao;
    }

    private static void createTables() throws SQLException {
        TableUtils.createTableIfNotExists(connectionSource, MovieEntity.class);
        TableUtils.createTableIfNotExists(connectionSource, WatchlistMovieEntity.class);
    }

    private static void createConnection() throws SQLException {
        connectionSource = new JdbcConnectionSource(DB_URL, username, password);
    }

}