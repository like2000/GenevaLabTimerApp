package ch.li.k.genevalabtimerapp.tictoc;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.view.View;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TicTocViewModel extends AndroidViewModel {

//    private static final String filename = "tictoc_output.csv";
//    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTRENAL_STORAGE = 1;
//    private static final String directory = Environment
//            .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
//            .getAbsolutePath();

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

//        this.initData();
    }

    void initData() {
//        System.out.println(directory + "/" + filename);

//        // Here, thisActivity is the current activity
//        if (ContextCompat.checkSelfPermission(getApplication(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED) {
//
//            // Permission is not granted
//            // Should we show an explanation?
//            if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) getApplication().getApplicationContext(),
//                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
//                // Show an explanation to the user *asynchronously* -- don't block
//                // this thread waiting for the user's response! After the user
//                // sees the explanation, try again to request the permission.
//            } else {
//                // No explanation needed; request the permission
//                ActivityCompat.requestPermissions((Activity) getApplication().getApplicationContext(),
//                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
//                        MY_PERMISSIONS_REQUEST_WRITE_EXTRENAL_STORAGE);
//
//                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
//                // app-defined int constant. The callback method gets the
//                // result of the request.
//            }
//        } else {
//            // File exist
//            try {
//                CSVParser parser = new CSVParser(new FileReader(directory + "/" + filename), CSVFormat.RFC4180);
//                for (CSVRecord record : parser.getRecords()) System.out.println(record);
//            } catch (IOException errRead) {
//                try {
//                    Random rng = new Random();
//                    CSVPrinter writer = new CSVPrinter(new FileWriter(directory + "/" + filename), CSVFormat.RFC4180);
//                    for (int i = 0; i < 10; i++) {
//                        writer.printRecord(new int[]{rng.nextInt(), rng.nextInt(), rng.nextInt(), rng.nextInt()});
//                    }
//                } catch (IOException errWrite) {
//                    errWrite.printStackTrace();
//                }
//                errRead.printStackTrace();
//            }
//            if (file.exists() && !file.isDirectory()) {
//                FileWriter mFileWriter = new FileWriter(path, true);
//                writer = new CSVWriter(mFileWriter);
//            } else {
//                writer = new CSVWriter(new FileWriter(path));
//            }
//            String[] data = {"Ship Name", "Scientist Name", "...",
//                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))};
//
//            writer.writeNext(data);
//            writer.close();
//        }
    }

    MutableLiveData<List<TicTocModel>> getMutableTicTocModelList() {
        return mutableTicTocModelList;
    }

    private void newEntry() {
        List<TicTocModel> ticTcModelList = mutableTicTocModelList.getValue();
        if (ticTcModelList == null)
            ticTcModelList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            ticTcModelList.add(new TicTocModel(new String[]{"a", "b"}));
        }

        mutableTicTocModelList.setValue(ticTcModelList);
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
