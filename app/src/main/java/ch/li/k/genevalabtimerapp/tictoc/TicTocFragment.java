package ch.li.k.genevalabtimerapp.tictoc;

import android.Manifest;
import android.arch.lifecycle.Observer;
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

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ch.li.k.genevalabtimerapp.R;
import ch.li.k.genevalabtimerapp.databinding.FragmentTicTocBinding;

public class TicTocFragment extends Fragment {

    private static final String filename = "tictoc_output.csv";
    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTRENAL_STORAGE = 1;
    private static final String directory = Environment
            .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            .getAbsolutePath();

    private FragmentTicTocBinding binding;

    public TicTocFragment() {
    }

    void initFileStream() {
        // Here, thisActivity is the current activity
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
            // File exist
            try {
                CSVParser parser = new CSVParser(new FileReader(directory + "/" + filename), CSVFormat.RFC4180);
                for (CSVRecord record : parser.getRecords()) System.out.println(record);
            } catch (IOException errRead) {
                try {
                    Random rng = new Random();
                    CSVPrinter writer = new CSVPrinter(new FileWriter(directory + "/" + filename), CSVFormat.RFC4180);
                    for (int i = 0; i < 10; i++) {
                        writer.printRecord(new int[]{rng.nextInt(), rng.nextInt(), rng.nextInt(), rng.nextInt()});
                    }
                } catch (IOException errWrite) {
                    errWrite.printStackTrace();
                }
                errRead.printStackTrace();
            }
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentTicTocBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(this);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initFileStream();

        RecyclerView recyclerView = getActivity().findViewById(R.id.rvTimesList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new TicTocAdapter());

        TicTocViewModel viewModel = ViewModelProviders.of(this).get(TicTocViewModel.class);
        viewModel.getMutableTicTocModelList().observe(this, new Observer<List<TicTocModel>>() {
            @Override
            public void onChanged(@Nullable List<TicTocModel> ticTocModels) {
                ((TicTocAdapter) recyclerView.getAdapter())
                        .setTicTocModelList((ArrayList<TicTocModel>) ticTocModels);
            }
        });

        binding.setTictoc(viewModel);
    }
}
