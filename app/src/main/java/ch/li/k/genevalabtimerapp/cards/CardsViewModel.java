package ch.li.k.genevalabtimerapp.cards;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.view.View;

import com.google.common.collect.Lists;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CardsViewModel extends ViewModel {

    private MutableLiveData<List<CardsModel>> cardsLiveData;

    public CardsViewModel() {
        cardsLiveData = new MutableLiveData<>();
        cardsLiveData.setValue(new ArrayList<>());
    }

    LiveData<List<CardsModel>> getCardsLiveData() throws IOException {
        cardsLiveData.getValue().addAll(CardsModel.readCards());
        return cardsLiveData;
    }

    public void addCard(View v, String type) {
        List<CardsModel> cardsList = new ArrayList<>();

        String[] data = {
                String.valueOf(LocalDateTime.now().atZone(ZoneId.of("Europe/Zurich")).toEpochSecond()),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
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
        String[] data = {
                String.valueOf(LocalDateTime.now().atZone(ZoneId.of("Europe/Zurich")).toEpochSecond()),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                type};
        this.cardsLiveData.getValue().add(new CardsModel(Lists.newArrayList(data)));
        this.cardsLiveData.setValue(Lists.newArrayList(new CardsModel(Lists.newArrayList(data))));
    }
}
