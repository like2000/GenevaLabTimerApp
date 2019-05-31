package ch.li.k.genevalabtimerapp.tictoc;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

public class TicTocViewModel extends AndroidViewModel {

    private static final String filename = "tictoc_output.csv";
    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTRENAL_STORAGE = 1;
    private static final String directory = Environment
            .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            .getAbsolutePath();

    private MutableLiveData<Boolean> newTrigger;
    private MutableLiveData<Boolean> readTrigger;
    private MutableLiveData<Boolean> deleteTrigger;

    private MutableLiveData<LocalDateTime> globalTimeStamp;
    private MutableLiveData<ArrayList<TicTocModel>> ticTocModelList;

    public TicTocViewModel(@NonNull Application application) {
        super(application);

        this.newTrigger = new MutableLiveData<>();
        this.readTrigger = new MutableLiveData<>();
        this.deleteTrigger = new MutableLiveData<>();

        this.globalTimeStamp = new MutableLiveData<>();
        this.ticTocModelList = new MutableLiveData<>();
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

    public void delete(View v) {
        new File(directory + "/" + filename).delete();
        Toast.makeText(getApplication(), "File: " + filename + " deleted!", Toast.LENGTH_SHORT).show();
        System.out.println("Deleted!");
//        try {
//            new FileWriter(directory + "/" + filename, false).close();
////            Toast.makeText(getApplication(), 1, 1);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public void doNothing(View v) {
        System.out.println("Do nothing!");
    }

    public void createGlobalTimeStamp(View v) {
        this.globalTimeStamp.setValue(LocalDateTime.now());
    }

    public class TicTocModel { // extends POJO
        /*
        Essentially an internal struct
        From: https://stackoverflow.com/questions/48020377/livedata-update-on-object-field-change: Your POJO object would look something like this
        */

        ArrayList<Integer> sets;
        LocalDateTime timeStamp;
        String exercise;

        public LocalDateTime getTimeStamp() {
            return timeStamp;
        }

        void setTimeStamp(LocalDateTime timeStamp) {
            this.timeStamp = timeStamp;
        }

        public ArrayList<Integer> getSets() {
            return sets;
        }

        void setSets(ArrayList<Integer> sets) {
            this.sets = sets;
        }

        public String getExercise() {
            return exercise;
        }

        void setExercise(String exercise) {
            this.exercise = exercise;
        }
    }
}
