package ch.li.k.genevalabtimerapp.cards;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.Environment;
import android.view.View;

import com.google.common.collect.Lists;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CardsViewModel extends ViewModel {

    private static final String filename = "geneva_data.csv";
    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTRENAL_STORAGE = 1;
    private static final String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            .getAbsolutePath();

    private MutableLiveData<List<CardsModel>> cardsLiveData;

    public CardsViewModel() {
        cardsLiveData = new MutableLiveData<>();
        cardsLiveData.setValue(new ArrayList<>());
    }

    LiveData<List<CardsModel>> getCardsLiveData() throws IOException {
        cardsLiveData.getValue().addAll(CardsModel.readCards());
        return cardsLiveData;
    }

//    public void setCardsLiveData(LiveData<List<CardsModel>> cardsLiveData) {
//        this.cardsLiveData = cardsLiveData;
//    }

    public void addCard(View v, String type) {
        List<CardsModel> cardsList = new ArrayList<>();

        String[] data = {LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                type};

        cardsList.add(new CardsModel(Lists.newArrayList(data)));
        try {
            CardsModel.writeCards(cardsList);
            cardsList = CardsModel.readCards();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.cardsLiveData.setValue(cardsList);
    }

    public void newCard(View v, String type) {
        System.out.println("Push button");
        String[] data = {LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                type};
        this.cardsLiveData.getValue().add(new CardsModel(Lists.newArrayList(data)));
        this.cardsLiveData.setValue(Lists.newArrayList(new CardsModel(Lists.newArrayList(data))));
    }

//    public void addCards(CardsModel cards) {
//        System.out.println(this.cardsLiveData.getValue());
//        this.cardsLiveData.getValue().add(cards);
//    }
}
