package ch.li.k.genevalabtimerapp.tictoc;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.view.View;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TicTocViewModel extends AndroidViewModel {

    private ExecutorService executorService;
    private MutableLiveData<TicTocModel> modelMutableLiveData;
    private MutableLiveData<LocalDateTime> timestampMutableLiveData;
    private MutableLiveData<List<TicTocModel>> mutableTicTocModelList;

    public TicTocViewModel(@NonNull Application application) {
        super(application);

        this.modelMutableLiveData = new MutableLiveData<>();
        this.mutableTicTocModelList = new MutableLiveData<>();

        this.timestampMutableLiveData = new MutableLiveData<>();
        this.timestampMutableLiveData.setValue(LocalDateTime.now());

        this.executorService = Executors.newSingleThreadExecutor();
    }

    public MutableLiveData<List<TicTocModel>> getMutableTicTocModelList() {
        return mutableTicTocModelList;
    }

    public void setMutableTicTocModelList(MutableLiveData<List<TicTocModel>> mutableTicTocModelList) {
        this.mutableTicTocModelList = mutableTicTocModelList;
    }

    private void newEntry() {
//        List<TicTocModel> modelList = new ArrayList<>();
//        TicTocModel model = this.modelMutableLiveData.getValue();
        TicTocModel model = new TicTocModel(new String[]{"a", "b"});
        modelMutableLiveData.setValue(model);
    }

    public void writeEntry(View v) {
        newEntry();
        try {
            this.modelMutableLiveData.getValue().writeCsv();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void newTimestamp(View v) {
        this.timestampMutableLiveData.setValue(LocalDateTime.now());
        System.out.println(this.timestampMutableLiveData.getValue().toString());
    }

    public MutableLiveData<LocalDateTime> getTimestampMutableLiveData() {
        return timestampMutableLiveData;
    }

    public void setTimestampMutableLiveData(MutableLiveData<LocalDateTime> timestampMutableLiveData) {
        this.timestampMutableLiveData = timestampMutableLiveData;
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
