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

    @NonNull
    @Override
    public CardsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View itemBinding;
        int layoutId = viewType;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, layoutId, parent, false);

        CardsViewHolder viewHolder = new CardsViewHolder(binding);
        return viewHolder;

//        if (viewType == ARMS_TYPE) {
//            FragmentCardsArmsItemBinding itemBinding = FragmentCardsArmsItemBinding.inflate(
//                    inflater, parent, false);
//            return new CardsViewHolder(itemBinding);
//        } else if (viewType == BACK_TYPE) {
//            FragmentCardsArmsItemBinding itemBinding = FragmentCardsArmsItemBinding.inflate(
//                    inflater, parent, false);
//            return new CardsViewHolder(itemBinding);
//        } else {
//            throw new RuntimeException("Unknown type!");
//        }
    }

    @Override
    public int getItemViewType(int position) {
        CardsModel card = cardsList.get(position);
        if (card.getType() == CardsModel.ViewType.ARMS) {
            return R.layout.fragment_cards_arms_item;
        } else if (card.getType() == CardsModel.ViewType.BACK) {
            return R.layout.fragment_cards_back_item;
        } else if (card.getType() == CardsModel.ViewType.CHEST) {
            return R.layout.support_simple_spinner_dropdown_item;
        } else {
//            throw new RuntimeException("Card type must be one of ARMS, BACK or CHEST!");
            return R.layout.fragment_cards_back_item;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull CardsViewHolder holder, int ix) {
        holder.bind(cardsList.get(ix));
    }

    @Override
    public int getItemCount() {
        return cardsList == null ? 0 : cardsList.size();
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

//    class CardsViewHolder extends RecyclerView.ViewHolder {
//
//        private final FragmentCardsArmsItemBinding binding;
//
//        public CardsViewHolder(FragmentCardsArmsItemBinding binding) {
//            super(binding.getRoot());
//
//            this.binding = binding;
//        }
//
//        public void bind(CardsModel cards) {
//            binding.setCards(cards);
//            binding.executePendingBindings();
//        }
//    }
}
