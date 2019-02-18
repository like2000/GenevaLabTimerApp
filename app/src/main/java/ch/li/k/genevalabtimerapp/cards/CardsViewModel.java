package ch.li.k.genevalabtimerapp.cards;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.Environment;

import com.google.common.collect.Lists;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CardsViewModel extends ViewModel {

    private static final String filename = "geneva_data.csv";
    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTRENAL_STORAGE = 1;
    private static final String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            .getAbsolutePath();
    private LiveData<List<CardsModel>> cardsLiveData;

    public CardsViewModel() {
        cardsLiveData = new MutableLiveData<>();
        ((MutableLiveData<List<CardsModel>>) cardsLiveData).setValue(new ArrayList<>());
    }

    public static void dump_data() throws IOException {
        String path = directory + File.separator + filename;
        File file = new File(path);
        CSVPrinter writer;

//        // Here, thisActivity is the current activity
//        if (ContextCompat.checkSelfPermission(this.context,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED) {
//
//            // Permission is not granted
//            // Should we show an explanation?
//            if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) this.context,
//                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
//                // Show an explanation to the user *asynchronously* -- don't block
//                // this thread waiting for the user's response! After the user
//                // sees the explanation, try again to request the permission.
//            } else {
//                // No explanation needed; request the permission
//                ActivityCompat.requestPermissions((Activity) this.context,
//                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
//                        MY_PERMISSIONS_REQUEST_WRITE_EXTRENAL_STORAGE);
//
//                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
//                // app-defined int constant. The callback method gets the
//                // result of the request.
//            }
//        } else {

        // File exist
        if (file.exists() && !file.isDirectory()) {
            FileWriter mFileWriter = new FileWriter(path, true);
            writer = new CSVPrinter(mFileWriter, CSVFormat.RFC4180);
        } else {
            writer = new CSVPrinter(new FileWriter(path), CSVFormat.RFC4180);
        }
        String[] data = {LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                "Lat Pulldowns", "50", "50", "50", "50"};

//        writer.printRecord(data);
        writer.close();
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

        String path = directory + File.separator + filename;
        Reader buffer = new FileReader(path);
        CSVParser parser = CSVParser.parse(buffer, CSVFormat.RFC4180);
        for (CSVRecord record : parser) {
            System.out.println(Lists.newArrayList(record));
            cardsLiveData.getValue().add(new CardsModel(Lists.newArrayList(record)));
        }
        return cardsLiveData;
    }

    public void setCardsLiveData(LiveData<List<CardsModel>> cardsLiveData) {
        this.cardsLiveData = cardsLiveData;
    }

    public void newCard() {
        System.out.println("Push button");
        String[] data = {LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                "Deadlifts", "50", "50", "50", "50"};
        CardsModel card = new CardsModel(Lists.newArrayList(data));
        this.cardsLiveData.getValue().add(card);
        System.out.println(this.cardsLiveData.getValue().size());
    }

    public void addCards(CardsModel cards) {
        System.out.println(this.cardsLiveData.getValue());
        this.cardsLiveData.getValue().add(cards);
    }
}
