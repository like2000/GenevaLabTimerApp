package ch.li.k.genevalabtimerapp.stats;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

@Database(entities = StatsRoomModel.class, version = 1, exportSchema = false)
public abstract class StatsRoomDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "stats_database";
    private static volatile StatsRoomDatabase INSTANCE;

    static StatsRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (StatsRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            StatsRoomDatabase.class,
                            DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public static void destroyDatabase() {
        INSTANCE = null;
    }

    public abstract StatsRoomDao statsDao();

    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }
}
