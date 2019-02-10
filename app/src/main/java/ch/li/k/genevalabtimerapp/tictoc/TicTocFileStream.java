package ch.li.k.genevalabtimerapp.tictoc;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.IOException;

public class TicTocFileStream {
    private static final String filename = "test_output.csv";
    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTRENAL_STORAGE = 1;
    private static final String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            .getAbsolutePath();
    private Context context;

    public TicTocFileStream(Context context) {
        this.context = context;
    }

    public void check() {

        String dir = Environment.getExternalStorageDirectory().getAbsolutePath();
        System.out.println(directory);
        System.out.println(dir);
    }

    public void write() throws IOException {
        String path = directory + File.separator + filename;
        File file = new File(path);
//        CSVWriter writer;
//
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
}
