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
    private TicTocViewModel viewModel;

    public static TicTocFragment newInstance() {
        return new TicTocFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentTicTocBinding.inflate(inflater, container, false);
        binding.setTictoc(new TicTocModel(new String[] {""}));
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Bit of binding to be done here?
        viewModel = ViewModelProviders.of(this).get(TicTocViewModel.class);
        // TODO: Use the ViewModel
    }
}
