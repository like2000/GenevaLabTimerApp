package ch.li.k.genevalabtimerapp.cards;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.List;

import ch.li.k.genevalabtimerapp.R;
import ch.li.k.genevalabtimerapp.databinding.FragmentCardsBinding;

public class CardsFragment extends Fragment {

    FragmentCardsBinding binding;
    CardsViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cards, container, false);
        viewModel = ViewModelProviders.of(this).get(CardsViewModel.class);
        binding.setCardsViewModel(viewModel);

        return binding.getRoot();
//        return inflater.inflate(R.layout.fragment_cards, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RecyclerView.Adapter adapter = new CardsAdapter();
        RecyclerView recyclerView = getActivity().findViewById(R.id.cardsList);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        // TODO: Use the ViewModel
//        try {
//            viewModel.getCardsLiveData().observe(this, ((CardsAdapter) adapter)::setCards);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        try {
            viewModel.getCardsLiveData().observe(this, (List<CardsModel> cardsModelList) -> {
                ((CardsAdapter) adapter).setCards(cardsModelList);
                Log.d(this.getTag(), "Observe change.");
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

//        FloatingActionButton fab = getActivity().findViewById(R.id.fabAddCard);
//        fab.setOnClickListener((l) -> {
////            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.select_dialog_item);
//            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Objects.requireNonNull(this.getContext()), android.R.layout.simple_selectable_list_item);
//            arrayAdapter.add("Back");
//            arrayAdapter.add("Chest");
//            arrayAdapter.add("General");
//
//            AlertDialog dialog = new AlertDialog.Builder(this.getContext())
//                    .setTitle("Selection")
//                    .setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            CardsModel cards = new CardsModel(LocalDateTime.now(), arrayAdapter.getItem(i),
//                                    12, 12, 12, 12);
//                            try {
//                                viewModel.getCardsLiveData();
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
////                            ((CardsAdapter) adapter).addCards();
////                            try {
////                                viewModel.getCardsLiveData().
////                                viewModel.dump_data();
////                            } catch (IOException e) {
////                                e.printStackTrace();
////                            }
//                        }
//                    })
//                    .create();
//            dialog.show();
//        });
    }

//    private List<CardsModel> defaultCardsList() {
//        List<CardsModel> list = new ArrayList<>();
//
//        list.add(new CardsModel(LocalDateTime.now(), "Squats",
//                12, 12, 12, 12));
//        list.add(new CardsModel(LocalDateTime.now(), "Deadlifts",
//                12, 12, 12, 12));
//
//        return list;
//    }
}
