package ch.li.k.genevalabtimerapp.cards;

import android.arch.persistence.room.Entity;
import android.databinding.BaseObservable;
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

@Entity(tableName = "exercise_table")
public class CardsModel extends BaseObservable {

    private static final String filename = "geneva_data.csv";
    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTRENAL_STORAGE = 1;
    private static final String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            .getAbsolutePath();

    private int set1, set2, set3, set4;
    private LocalDateTime timestamp;
    private String[] exercise;
    private ViewType type;
    private int length;
    private int id;

    public CardsModel(String[] cardsData) {
        // 1d list of cards data - entry 0 and 1 are ids; then follow pattern
    }

    public CardsModel(LocalDateTime timestamp, String type) {
        this.timestamp = timestamp;
        this.type = string2ViewType(type);
    }

    public CardsModel(LocalDateTime timestamp, String exercise,
                      int set1, int set2, int set3, int set4) {
        this.set1 = set1;
        this.set2 = set2;
        this.set3 = set3;
        this.set4 = set4;
        this.timestamp = timestamp;
    }

    public CardsModel(ArrayList<String> stringArrayList) {
        this.id = Integer.parseInt(stringArrayList.get(0));
        this.timestamp = LocalDateTime.parse(stringArrayList.get(1), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.type = string2ViewType(stringArrayList.get(2));
    }

    static List<CardsModel> readCards() throws IOException {
        List<CardsModel> cardsList = new ArrayList<>();
        String path = directory + File.separator + filename;
        Reader buffer = new FileReader(path);
        CSVParser parser = CSVParser.parse(buffer, CSVFormat.RFC4180);
        for (CSVRecord record : parser) {
            System.out.println(Lists.newArrayList(record));
            cardsList.add(new CardsModel(Lists.newArrayList(record)));
        }
        return cardsList;
    }

    static void writeCards(List<CardsModel> cardsList) throws IOException {

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
            writer = new CSVPrinter(new FileWriter(path, true), CSVFormat.RFC4180);
        } else {
            writer = new CSVPrinter(new FileWriter(path, false), CSVFormat.RFC4180);
        }

        for (CardsModel card : cardsList) {
            String[] data = {
                    String.valueOf(card.id),
                    card.timestamp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                    card.type.name(),
                    String.valueOf(card.set1),
                    String.valueOf(card.set2),
                    String.valueOf(card.set3),
                    String.valueOf(card.set4)};
            writer.printRecord((Object[]) data);
        }
        writer.close();
    }

    void writeCardsData(String[] cardsData) {
        // 1d list of cards data - entry 0 and 1 are ids; then follow pattern
    }

    ViewType getType() {
        return type;
    }

    private ViewType string2ViewType(String type) {
        if (type.toUpperCase().equals("BACK")) {
            return ViewType.BACK;
        } else if (type.toUpperCase().equals("CHEST")) {
            return ViewType.CHEST;
        } else
            throw new RuntimeException("Unknow card type: " + type);
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public enum ViewType {
        ARMS,
        BACK,
        CHEST;
    }
// TODO: inlcude more business logic - viewModel then just exposes observables.
}
