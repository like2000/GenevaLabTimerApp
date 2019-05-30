package ch.li.k.genevalabtimerapp.tictoc;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TicTocViewModel extends AndroidViewModel {

    private ExecutorService executorService;

    private MutableLiveData<List<Duration>> duration;
    private MutableLiveData<List<LocalDateTime>> stopTime;
    private MutableLiveData<List<LocalDateTime>> startTime;
    private MutableLiveData<List<LocalDateTime>> timeStamp;

    private MutableLiveData<LocalDateTime> globalTimeStamp;
    private MutableLiveData<ArrayList<TicTocModel>> ticTocModelList;

    public TicTocViewModel(@NonNull Application application) {
        super(application);

        this.duration = new MutableLiveData<>();
        this.stopTime = new MutableLiveData<>();
        this.startTime = new MutableLiveData<>();
        this.timeStamp = new MutableLiveData<>();

        this.globalTimeStamp = new MutableLiveData<>();
        this.ticTocModelList = new MutableLiveData<>();

        this.executorService = Executors.newSingleThreadExecutor();

        initData();
    }

    public void initData() {
        Random rng = new Random();

        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(rng.nextInt());
        arrayList.add(rng.nextInt());

        TicTocModel model = new TicTocModel();
        model.setTimeStamp(LocalDateTime.now());
        model.setExercise("Barbell Curls");
        model.setSets(arrayList);

        ArrayList<TicTocModel> modelList = (ArrayList<TicTocModel>) ticTocModelList.getValue();
        if (modelList == null)
            modelList = new ArrayList<>();
        modelList.add(model);
        ticTocModelList.setValue(modelList);
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

    public MutableLiveData<ArrayList<TicTocModel>> getTicTocModelList() {
        return ticTocModelList;
    }

    public void setTicTocModelList(MutableLiveData<ArrayList<TicTocModel>> ticTocModelList) {
        this.ticTocModelList = ticTocModelList;
    }

    public void newEntry(View v) {
        ArrayList<TicTocModel> ticTcModelList = ticTocModelList.getValue();
        if (ticTcModelList == null)
            ticTcModelList = new ArrayList<>();

        ArrayList<Integer> setsList = new ArrayList<>();
        TicTocModel model = new TicTocModel();
        Random rng = new Random();

        for (int i = 0; i < 10; i++) {
            setsList.add(rng.nextInt());
            setsList.add(rng.nextInt());

            model.setTimeStamp(LocalDateTime.now());
            model.setExercise("Incline Curls");
            model.setSets(setsList);
            ticTcModelList.add(model);
        }

        ticTocModelList.setValue(ticTcModelList);
        Log.d("DEBUG", "Add new entry");
    }

    public void createGlobalTimeStamp(View v) {
        this.globalTimeStamp.setValue(LocalDateTime.now());
    }

    public class TicTocModel { // extends POJO
        /*
        Essentially an internal struct
        From: https://stackoverflow.com/questions/48020377/livedata-update-on-object-field-change: Your POJO object would look something like this
        */

        LocalDateTime timeStamp;
        ArrayList<Integer> sets;
        String exercise;

        public LocalDateTime getTimeStamp() {
            return timeStamp;
        }

        public void setTimeStamp(LocalDateTime timeStamp) {
            this.timeStamp = timeStamp;
        }

        public ArrayList<Integer> getSets() {
            return sets;
        }

        public void setSets(ArrayList<Integer> sets) {
            this.sets = sets;
        }

        public String getExercise() {
            return exercise;
        }

        public void setExercise(String exercise) {
            this.exercise = exercise;
        }
    }
}
