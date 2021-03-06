package ch.li.k.genevalabtimerapp.tictoc;

import android.Manifest;
import android.arch.lifecycle.ViewModelProviders;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ch.li.k.genevalabtimerapp.databinding.FragmentTicTocBinding;

public class TicTocFragment extends Fragment {

    private static final String filename = "tictoc_output.csv";
    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTRENAL_STORAGE = 1;
    private static final String directory = Environment
            .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            .getAbsolutePath();

    private TicTocAdapter adapter;
    private RecyclerView recyclerView;
    private FragmentTicTocBinding binding;

    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    public TicTocFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentTicTocBinding.inflate(inflater, container, false);
        recyclerView = binding.rvTimesList;

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        try {
            initFileStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        adapter = new TicTocAdapter();

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        TicTocViewModel viewModel = ViewModelProviders.of(this).get(TicTocViewModel.class);
        viewModel.getTicTocModelList().observe(this, adapter::setTicTocModelList);

        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);
    }

    void initFileStream() throws IOException {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_WRITE_EXTRENAL_STORAGE);
                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // First clean everything
            // ======================
            int n_rows, n_cols;
            List<CSVRecord> buffer;
            File file = new File(directory + "/" + filename);

            if (file.exists()) {
                CSVParser parser = new CSVParser(new FileReader(directory + "/" + filename), CSVFormat.RFC4180);
                buffer = parser.getRecords();
                n_rows = buffer.size();

//                System.out.println("\n\n--> Printing output buffer:");
//                buffer.forEach(System.out::println);
            } else {
                CSVPrinter writer = new CSVPrinter(new FileWriter(directory + "/" + filename), CSVFormat.RFC4180);
                writer.printRecord("Timestamp", "Start", "Stop", "Duration");
                writer.close();
            }
        }
    }
}