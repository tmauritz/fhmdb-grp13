package at.ac.fhcampuswien.fhmdb.database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "watchlistMovie")
public class WatchlistMovieEntity {

    @DatabaseField(id = true)
    private long watchlistId;

    @DatabaseField (unique = true)
    private String apiId;

    //@DatabaseField (foreign = true)
    //private MovieEntity movie;

    public WatchlistMovieEntity(){
    }


    public WatchlistMovieEntity(String apiId) {
        this.apiId = apiId;
    }

    public String getApiId() {
        return apiId;
    }

}
