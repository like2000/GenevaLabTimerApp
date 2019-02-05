package ch.li.k.genevalabtimerapp.cards;

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

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ch.li.k.genevalabtimerapp.R;

public class CardsFragment extends Fragment {

    private CardsViewModel viewModel;

    public static CardsFragment newInstance() {
        return new CardsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cards, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RecyclerView.Adapter adapter = new CardsAdapter(defaultCardsList(), this.getContext());
        RecyclerView recyclerView = getActivity().findViewById(R.id.cardsList);
        CardsViewModel viewModel = ViewModelProviders.of(this).get(CardsViewModel.class);
        // TODO: Use the ViewModel
        try {
            viewModel.dump_data();
        } catch (IOException e) {
            e.printStackTrace();
        }

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
    }

    private List<CardsModel> defaultCardsList() {
        List<CardsModel> list = new ArrayList<>();

        list.add(new CardsModel(LocalDateTime.now(), "Squats",
                12, 12, 12, 12));
        list.add(new CardsModel(LocalDateTime.now(), "Deadlifts",
                12, 12, 12, 12));

        return list;
    }
}
