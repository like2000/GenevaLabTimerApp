package ch.li.k.genevalabtimerapp.stats;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StatsViewModel extends AndroidViewModel {

    private StatsRoomDao dao;
    private ExecutorService executorService;

    public StatsViewModel(@NonNull Application application) {
        super(application);

        dao = StatsRoomDatabase.getDatabase(application).statsDao();
        executorService = Executors.newSingleThreadExecutor();

        this.createSampleStats();
        System.out.println(dao.getAll());
    }

    LiveData<List<StatsRoomModel>> getAllStats() {
        return dao.getAll();
    }

    void createSampleStats() {
        for (int i = 0; i < 5; i++) {
            LocalDateTime arg1 = LocalDateTime.now();
            Integer arg2 = new Random().nextInt();
            Integer arg3 = new Random().nextInt();
            this.insertStats(new StatsRoomModel(arg1.toString(), arg2.toString(), arg3.toString()));
        }
    }

    void insertStats(StatsRoomModel stats) {
        executorService.execute(() -> dao.insertStats(stats));
    }

    void deleteAllStats() {
        executorService.execute(() -> dao.deleteAll());
    }
}
