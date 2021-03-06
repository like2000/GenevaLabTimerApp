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

    public CardsFragment() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cards, container, false);
        viewModel = ViewModelProviders.of(this).get(CardsViewModel.class);
        binding.setCardsViewModel(viewModel);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RecyclerView.Adapter adapter = new CardsAdapter();
        RecyclerView recyclerView = getActivity().findViewById(R.id.cardsList);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        // TODO: Use the ViewModel
        try {
//            viewModel.getCardsLiveData().observe(this, ((CardsAdapter) adapter)::setCards);
            viewModel.getCardsLiveData().observe(this, (List<CardsModel> cardsModelList) -> {
                ((CardsAdapter) adapter).setCards(cardsModelList);
                recyclerView.scrollToPosition(cardsModelList.size() - 1);
                Log.d(this.getTag(), String.format("Observe change. Scroll to %d", cardsModelList.size() - 1));
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
