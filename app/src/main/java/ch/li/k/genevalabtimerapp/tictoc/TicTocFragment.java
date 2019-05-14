package ch.li.k.genevalabtimerapp.tictoc;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

        TicTocViewModel viewModel = ViewModelProviders.of(this).get(TicTocViewModel.class);
        binding.setTictoc(viewModel);
    }
}
