package ch.li.k.genevalabtimerapp.tictoc;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.view.View;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TicTocViewModel extends AndroidViewModel {

    private ExecutorService executorService;

    private MutableLiveData<List<Duration>> duration;
    private MutableLiveData<List<LocalDateTime>> stopTime;
    private MutableLiveData<List<LocalDateTime>> startTime;
    private MutableLiveData<List<LocalDateTime>> timeStamp;

    private MutableLiveData<LocalDateTime> globalTimeStamp;

    public TicTocViewModel(@NonNull Application application) {
        super(application);

        this.duration = new MutableLiveData<>();
        this.stopTime = new MutableLiveData<>();
        this.startTime = new MutableLiveData<>();
        this.timeStamp = new MutableLiveData<>();

        this.globalTimeStamp = new MutableLiveData<>();

        this.executorService = Executors.newSingleThreadExecutor();
    }

    public MutableLiveData<List<Duration>> getDuration() {
        return duration;
    }

    public void setDuration(MutableLiveData<List<Duration>> duration) {
        this.duration = duration;
    }

    public MutableLiveData<List<LocalDateTime>> getStopTime() {
        return stopTime;
    }

    public void setStopTime(MutableLiveData<List<LocalDateTime>> stopTime) {
        this.stopTime = stopTime;
    }

    public MutableLiveData<List<LocalDateTime>> getStartTime() {
        return startTime;
    }

    public void setStartTime(MutableLiveData<List<LocalDateTime>> startTime) {
        this.startTime = startTime;
    }

    public MutableLiveData<List<LocalDateTime>> getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(MutableLiveData<List<LocalDateTime>> timeStamp) {
        this.timeStamp = timeStamp;
    }

    public MutableLiveData<LocalDateTime> getGlobalTimeStamp() {
        return globalTimeStamp;
    }

    public void setGlobalTimeStamp(MutableLiveData<LocalDateTime> globalTimeStamp) {
        this.globalTimeStamp = globalTimeStamp;
    }

    public void newEntry(View v) {
//        List<TicTocModel> ticTcModelList = mutableTicTocModelList.getValue();
//        if (ticTcModelList == null)
//            ticTcModelList = new ArrayList<>();
//
//        for (int i = 0; i < 10; i++) {
//            ticTcModelList.add(new TicTocModel(new String[]{"a", "b"}));
//        }
//
//        mutableTicTocModelList.setValue(ticTcModelList);
    }

    public void createGlobalTimeStamp(View v) {
        this.globalTimeStamp.setValue(LocalDateTime.now());
    }

    public class TicTocModel {
        // Essentially an internal struct
    }
}
