package ch.li.k.genevalabtimerapp.cards;

import android.app.AlertDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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

        RecyclerView.Adapter adapter = new CardsAdapter();
        RecyclerView recyclerView = getActivity().findViewById(R.id.cardsList);
        CardsViewModel viewModel = ViewModelProviders.of(this).get(CardsViewModel.class);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        // TODO: Use the ViewModel
        try {
            viewModel.getCardsLiveData().observe(this, ((CardsAdapter) adapter)::setCards);
        } catch (IOException e) {
            e.printStackTrace();
        }

        FloatingActionButton fab = getActivity().findViewById(R.id.fabAddCard);
        fab.setOnClickListener((l) -> {
//            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.select_dialog_item);
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Objects.requireNonNull(this.getContext()), android.R.layout.simple_selectable_list_item);
            arrayAdapter.add("Back");
            arrayAdapter.add("Chest");
            arrayAdapter.add("General");

            AlertDialog dialog = new AlertDialog.Builder(this.getContext())
                    .setTitle("Selection")
                    .setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            CardsModel cards = new CardsModel(LocalDateTime.now(), arrayAdapter.getItem(i),
                                    12, 12, 12, 12);
                            try {
                                viewModel.getCardsLiveData();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
//                            ((CardsAdapter) adapter).addCards();
//                            try {
//                                viewModel.getCardsLiveData().
//                                viewModel.dump_data();
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
                        }
                    })
                    .create();
            dialog.show();
        });
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
