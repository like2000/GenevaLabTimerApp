package ch.li.k.genevalabtimerapp.tictoc;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ch.li.k.genevalabtimerapp.R;
import ch.li.k.genevalabtimerapp.databinding.FragmentTicTocBinding;

public class TicTocFragment extends Fragment {

    private FragmentTicTocBinding binding;

    public TicTocFragment() {
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
