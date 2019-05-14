package ch.li.k.genevalabtimerapp.tictoc;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.view.View;

import java.io.IOException;
import java.time.LocalDateTime;

public class TicTocViewModel extends AndroidViewModel {

    private MutableLiveData<TicTocModel> modelMutableLiveData;
    private MutableLiveData<LocalDateTime> timestampMutableLiveData;

    public TicTocViewModel(@NonNull Application application) {
        super(application);

        this.modelMutableLiveData = new MutableLiveData<>();

        this.timestampMutableLiveData = new MutableLiveData<>();
        this.timestampMutableLiveData.setValue(LocalDateTime.now());

    }

    public void newEntry() {
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
