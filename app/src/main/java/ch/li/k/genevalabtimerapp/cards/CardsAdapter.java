package ch.li.k.genevalabtimerapp.cards;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.android.databinding.library.baseAdapters.BR;

import java.util.ArrayList;
import java.util.List;

import ch.li.k.genevalabtimerapp.R;

public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.CardsViewHolder> {

    private List<CardsModel> cardsList = new ArrayList<>();

    CardsAdapter() {
    }

    @NonNull
    @Override
    public CardsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), viewType, parent, false);

        return new CardsViewHolder(binding);
    }

    @Override
    public int getItemViewType(int position) {
        CardsModel card = cardsList.get(position);
        if (card.getType() == CardsModel.ViewType.BACK) {
            return R.layout.fragment_cards_back_item;
        } else if (card.getType() == CardsModel.ViewType.CHEST) {
            return R.layout.fragment_cards_chest_item;
        } else {
//            throw new RuntimeException("Card type must be one of ARMS, BACK or CHEST!");
            return R.layout.fragment_cards_back_item;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull CardsViewHolder holder, int position) {
        holder.bind(cardsList.get(position));
    }

    @Override
    public int getItemCount() {
        return cardsList == null ? 0 : cardsList.size();
    }

    public void setCards(List<CardsModel> cardsList) {
        this.cardsList = cardsList;
        notifyDataSetChanged();
    }

    class CardsViewHolder extends RecyclerView.ViewHolder {

        private final ViewDataBinding binding;

        CardsViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }

        void bind(CardsModel cards) {
            binding.setVariable(BR.cards, cards);
            binding.executePendingBindings();
        }
    }
}
