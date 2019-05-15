package ch.li.k.genevalabtimerapp.tictoc;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.view.View;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TicTocViewModel extends AndroidViewModel {

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
    }

    public MutableLiveData<List<TicTocModel>> getMutableTicTocModelList() {
        return mutableTicTocModelList;
    }

    public void setMutableTicTocModelList(MutableLiveData<List<TicTocModel>> mutableTicTocModelList) {
        this.mutableTicTocModelList = mutableTicTocModelList;
    }

    private void newEntry() {
        List<TicTocModel> ticTcModelList = mutableTicTocModelList.getValue();
        ticTcModelList.add(new TicTocModel(new String[]{"a", "b"}));
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
