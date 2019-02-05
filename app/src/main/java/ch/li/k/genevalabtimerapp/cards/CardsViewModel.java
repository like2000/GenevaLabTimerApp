package ch.li.k.genevalabtimerapp.cards;

import android.Manifest;
import android.app.Activity;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CardsViewModel extends ViewModel {

    // TODO: Implement the ViewModel
    private static final String filename = "geneva_data.csv";
    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTRENAL_STORAGE = 1;
    private static final String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            .getAbsolutePath();

    public static void dump_data() throws IOException {
        String path = directory + File.separator + filename;
        File file = new File(path);
        CSVWriter writer;

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
//            // File exist
        if (file.exists() && !file.isDirectory()) {
            FileWriter mFileWriter = new FileWriter(path, true);
            writer = new CSVWriter(mFileWriter);
        } else {
            writer = new CSVWriter(new FileWriter(path));
        }
        String[] data = {LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                "Lat Pulldowns", "50", "50", "50", "50"};

        writer.writeNext(data);
        writer.close();
    }
}
