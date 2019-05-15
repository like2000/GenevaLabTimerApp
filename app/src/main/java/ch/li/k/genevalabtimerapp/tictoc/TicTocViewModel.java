package ch.li.k.genevalabtimerapp.tictoc;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.view.View;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TicTocViewModel extends AndroidViewModel {

    private static final String filename = "tictoc_output.csv";
    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTRENAL_STORAGE = 1;
    private static final String directory = Environment
            .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            .getAbsolutePath();

    private ExecutorService executorService;
    private MutableLiveData<TicTocModel> mutableTicTocModel;
    private MutableLiveData<LocalDateTime> mutableTimestamp;
    private MutableLiveData<List<TicTocModel>> mutableTicTocModelList;

    public TicTocViewModel(@NonNull Application application) {
        super(application);

        this.mutableTicTocModel = new MutableLiveData<>();
        this.mutableTicTocModelList = new MutableLiveData<>();

        this.mutableTimestamp = new MutableLiveData<>();
        this.mutableTimestamp.setValue(LocalDateTime.now());

        this.executorService = Executors.newSingleThreadExecutor();

        this.initData();
    }

    void initData() {
        System.out.println(directory + "/" + filename);
        try {
            CSVParser parser = new CSVParser(new FileReader(directory + "/" + filename), CSVFormat.RFC4180);
            for (CSVRecord record : parser.getRecords()) System.out.println(record);
        } catch (IOException errRead) {
            try {
                Random rng = new Random();
                CSVPrinter writer = new CSVPrinter(new FileWriter(directory + "/" + filename), CSVFormat.RFC4180);
                for (int i = 0; i < 10; i++) {
                    writer.printRecord(new int[]{rng.nextInt(), rng.nextInt(), rng.nextInt(), rng.nextInt()});
                }
            } catch (IOException errWrite) {
                errWrite.printStackTrace();
            }
            errRead.printStackTrace();
        }
    }

    public MutableLiveData<List<TicTocModel>> getMutableTicTocModelList() {
        return mutableTicTocModelList;
    }

    public void setMutableTicTocModelList(MutableLiveData<List<TicTocModel>> mutableTicTocModelList) {
        this.mutableTicTocModelList = mutableTicTocModelList;
    }

    private void newEntry() {
        List<TicTocModel> ticTcModelList = mutableTicTocModelList.getValue();
        ticTcModelList = new ArrayList<>();
        try {
            for (int i = 0; i < 10; i++) {
                ticTcModelList.add(new TicTocModel(new String[]{"a", "b"}));
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        mutableTicTocModelList.setValue(ticTcModelList);
        System.out.println(ticTcModelList);
    }

    public void writeEntry(View v) {
        newEntry();
//        try {
//            this.modelMutableLiveData.getValue().writeCsv();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public MutableLiveData<LocalDateTime> getMutableTimestamp() {
        return mutableTimestamp;
    }

    public void newTimestamp(View v) {
        this.mutableTimestamp.setValue(LocalDateTime.now());
    }

//    public TicTocViewModel(@NonNull Application application) {
//        super(application);
//    }
//
//    @NonNull
//    @Override
//    public <T extends Application> T getApplication() {
//        return super.getApplication();
//    }
//
//    @Override
//    protected void onCleared() {
//        super.onCleared();
//    }
}
