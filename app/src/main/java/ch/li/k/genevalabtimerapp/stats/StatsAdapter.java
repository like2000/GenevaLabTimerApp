package ch.li.k.genevalabtimerapp.stats;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ch.li.k.genevalabtimerapp.R;
import ch.li.k.genevalabtimerapp.databinding.FragmentStatsItemBinding;

public class StatsAdapter extends RecyclerView.Adapter<StatsAdapter.StatsViewHolder> {

    List<StatsRoomModel> statsList = new ArrayList<>();

    @NonNull
    @Override
    public StatsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int ix) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        FragmentStatsItemBinding itemBinding = FragmentStatsItemBinding.inflate(
                inflater, parent, false);

        return new StatsViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull StatsViewHolder holder, int ix) {
        holder.bind(statsList.get(ix));
    }

    @Override
    public int getItemCount() {
        try {
            return statsList.size();
        } catch (NullPointerException exception) {
            return 0;
        }
    }

    void setStats(List<StatsRoomModel> statsList) {
        this.statsList = statsList;
        notifyDataSetChanged();
    }

    void addStats(StatsRoomModel stats) {
        statsList.add(stats);
        notifyDataSetChanged();
    }

    void removeStats() {
        statsList.clear();
        notifyDataSetChanged();
    }

    public class StatsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final FragmentStatsItemBinding binding;
        public TextView tvDate, tvEvent, tvTimestamp, tvDuration;

        public StatsViewHolder(FragmentStatsItemBinding binding) {
            super(binding.getRoot());

            this.binding = binding;

            tvDate = itemView.findViewById(R.id.tvDate);
            tvEvent = itemView.findViewById(R.id.tvEvent);
            tvTimestamp = itemView.findViewById(R.id.tvTimestamp);
        }

        public void bind(StatsRoomModel stats) {
            binding.setStats(stats);
            binding.executePendingBindings();
        }

        @Override
        public void onClick(View view) {

        }
    }
}
