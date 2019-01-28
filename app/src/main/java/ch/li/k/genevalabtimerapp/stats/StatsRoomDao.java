package ch.li.k.genevalabtimerapp.stats;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface StatsRoomDao {

    @Query("SELECT * FROM stats_table")
    LiveData<List<StatsRoomModel>> getAll();

    @Query("SELECT COUNT(*) FROM stats_table")
    int rowCount();

    @Query("DELETE FROM stats_table")
    void deleteAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertStats(StatsRoomModel... stats);

    @Update
    void update(StatsRoomModel... stats);

    @Delete
    void delete(StatsRoomModel... stats);
}
