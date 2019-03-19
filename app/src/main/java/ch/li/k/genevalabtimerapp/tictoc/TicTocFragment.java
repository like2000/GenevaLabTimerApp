package ch.li.k.genevalabtimerapp.tictoc;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ch.li.k.genevalabtimerapp.R;

public class TicTocFragment extends Fragment {

    private TicTocViewModel mViewModel;

    public static TicTocFragment newInstance() {
        return new TicTocFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tic_toc, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Bit of binding to be done here?
        mViewModel = ViewModelProviders.of(this).get(TicTocViewModel.class);
        // TODO: Use the ViewModel
    }
}
