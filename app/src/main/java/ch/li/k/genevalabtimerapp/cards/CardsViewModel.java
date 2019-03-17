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

//    public void getAllCards() throws IOException {
//        String path = directory + File.separator + filename;
//        CSVParser parser = CSVParser.parse(path, CSVFormat.RFC4180);
//        for (CSVRecord record : parser) {
//            System.out.println(path);
//            System.out.println(record);
//        }
//    }

//    public void getCardAt(int i) throws IOException {
//        CSVParser parser = CSVParser.parse(filename, CSVFormat.RFC4180);
//        for (CSVRecord record : parser) {
//            System.out.println(record);
//        }
//    }

    LiveData<List<CardsModel>> getCardsLiveData() throws IOException {

//        String path = directory + File.separator + filename;
//        Reader buffer = new FileReader(path);
//        CSVParser parser = CSVParser.parse(buffer, CSVFormat.RFC4180);
//        for (CSVRecord record : parser) {
//            System.out.println(Lists.newArrayList(record));
//            cardsLiveData.getValue().add(new CardsModel(Lists.newArrayList(record)));
//        }
        cardsLiveData.getValue().addAll(CardsModel.readCards());
        return cardsLiveData;
    }

//    public void setCardsLiveData(LiveData<List<CardsModel>> cardsLiveData) {
//        this.cardsLiveData = cardsLiveData;
//    }

    public void addCard(View v, String type) {
        List<CardsModel> cardsList = new ArrayList<>();

        String[] data = new String[]{
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

    public void newCard(View v) {
        System.out.println("Push button");
        String[] data = {LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                "Deadlifts", "50", "50", "50", "50"};
        this.cardsLiveData.getValue().add(new CardsModel(Lists.newArrayList(data)));
        this.cardsLiveData.setValue(Lists.newArrayList(new CardsModel(Lists.newArrayList(data))));
//        System.out.println(this.cardsLiveData);
    }

//    public void addCards(CardsModel cards) {
//        System.out.println(this.cardsLiveData.getValue());
//        this.cardsLiveData.getValue().add(cards);
//    }
}
