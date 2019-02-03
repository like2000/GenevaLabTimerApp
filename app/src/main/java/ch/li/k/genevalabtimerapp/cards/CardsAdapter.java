package ch.li.k.genevalabtimerapp.cards;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ch.li.k.genevalabtimerapp.databinding.FragmentStatsItemBinding;

public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.CardsViewHolder> {
    {

    }

    @NonNull
    @Override
    public CardsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        FragmentStatsItemBinding itemBinding = FragmentStatsItemBinding.inflate(
                inflater, parent, false);

        return new CardsViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CardsViewHolder holder, int ix) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class CardsViewHolder extends RecyclerView.ViewHolder {
        public CardsViewHolder(FragmentStatsItemBinding binding) {
            super(binding.getRoot());
        }
    }
}
