package ch.li.k.genevalabtimerapp.cards;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ch.li.k.genevalabtimerapp.databinding.FragmentCardsItemBinding;

public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.CardsViewHolder> {

//    Context context;
    List<CardsModel> cardsList = new ArrayList<>();

//    public CardsAdapter(Context context) {
//        this.context = context;
//    }

    @NonNull
    @Override
    public CardsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        FragmentCardsItemBinding itemBinding = FragmentCardsItemBinding.inflate(
                inflater, parent, false);

        return new CardsViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CardsViewHolder holder, int ix) {
        holder.bind(cardsList.get(ix));
    }

    @Override
    public int getItemCount() {
        try {
            return cardsList.size();
        } catch (NullPointerException exception) {
            return 0;
        }
    }

    public void setCards(List<CardsModel> cardsList) {
        this.cardsList = cardsList;
        notifyDataSetChanged();
    }

    public void addCards(CardsModel cards) {
        this.cardsList.add(cards);
        notifyDataSetChanged();
    }

    class CardsViewHolder extends RecyclerView.ViewHolder {

        private final FragmentCardsItemBinding binding;

        public CardsViewHolder(FragmentCardsItemBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }

        public void bind(CardsModel cards) {
            binding.setCards(cards);
            binding.executePendingBindings();
        }
    }
}
