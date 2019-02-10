package ch.li.k.genevalabtimerapp.cards;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ch.li.k.genevalabtimerapp.databinding.FragmentCardsArmsItemBinding;

public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.CardsViewHolder> {

    //    Context context;
    List<CardsModel> cardsList = new ArrayList<>();

//    public CardsAdapter(Context context) {
//        this.context = context;
//    }

    @NonNull
    @Override
    public CardsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View itemBinding;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if (viewType == 0) {
            FragmentCardsArmsItemBinding itemBinding = FragmentCardsArmsItemBinding.inflate(
                    inflater, parent, false);
            return new CardsViewHolder(itemBinding);
        } else {
            FragmentCardsArmsItemBinding itemBinding = FragmentCardsArmsItemBinding.inflate(
                    inflater, parent, false);
            return new CardsViewHolder(itemBinding);
        }
    }

    @Override
    public int getItemViewType(int position) {
//        Multiple
        return super.getItemViewType(position);
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

//    public void addCards(CardsModel cards) {
//        this.cardsList.add(cards);
//        notifyDataSetChanged();
//    }

    class CardsViewHolder extends RecyclerView.ViewHolder {

        private final FragmentCardsArmsItemBinding binding;

        public CardsViewHolder(FragmentCardsArmsItemBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }

        public void bind(CardsModel cards) {
            binding.setCards(cards);
            binding.executePendingBindings();
        }
    }
}
