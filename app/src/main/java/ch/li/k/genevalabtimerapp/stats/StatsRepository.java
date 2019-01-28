package ch.li.k.genevalabtimerapp.stats;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import java.util.List;

public class StatsRepository {

    private StatsRoomDao dao;
    private LiveData<List<StatsRoomModel>> statsList;

    public StatsRepository(Application application) {
        StatsRoomDatabase db = StatsRoomDatabase.getDatabase(application.getApplicationContext());
        dao = db.statsDao();
        statsList = dao.getAll();
    }

    public StatsRepository(MutableLiveData<List<StatsRoomModel>> statsList) {
        this.statsList = statsList;
    }

    public LiveData<List<StatsRoomModel>> getStatsList() {
        return statsList;
    }

    public void deleteAll() {
        new deleteAsynchTask(dao).execute();
    }

    //    public static StatsRepository getSample() {
//        return new StatsRepository();
//    }

    private static class deleteAsynchTask extends AsyncTask<Void, Void, Void> {

        private StatsRoomDao dao;

        deleteAsynchTask(StatsRoomDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao.deleteAll();
            return null;
        }
    }
}
