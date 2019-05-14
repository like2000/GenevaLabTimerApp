package ch.li.k.genevalabtimerapp.tictoc;

import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import ch.li.k.genevalabtimerapp.BR;
import ch.li.k.genevalabtimerapp.databinding.FragmentTicTocItemBinding;

// Best resource - George Mount: https://medium.com/androiddevelopers/android-data-binding-recyclerview-db7c40d9f0e4
public class TicTocAdapter extends RecyclerView.Adapter<TicTocAdapter.TicTocViewHolder> {

    private ArrayList<TicTocModel> ticTocModelList = new ArrayList<>();

    @NonNull
    @Override
    public TicTocViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding = FragmentTicTocItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);

        return new TicTocViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TicTocViewHolder holder, int position) {
        holder.bind(ticTocModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void setTicTocModelList(ArrayList<TicTocModel> ticTocModelList) {
        this.ticTocModelList = ticTocModelList;
        notifyDataSetChanged();
    }

    public class TicTocViewHolder extends RecyclerView.ViewHolder {
        private final ViewDataBinding binding;

        public TicTocViewHolder(@NonNull ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(TicTocModel model) {
            binding.setVariable(BR.tictoc, model);
            binding.executePendingBindings();
        }
    }
}
