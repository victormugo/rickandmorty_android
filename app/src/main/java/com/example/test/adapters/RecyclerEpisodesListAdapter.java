package com.example.test.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.databinding.ItemListEpisodesBinding;
import com.example.test.interfaces.RecyclerViewBaseClickListener;
import com.example.test.models.episodes.VoEpisodes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Victor
 */
public class RecyclerEpisodesListAdapter extends RecyclerView.Adapter<RecyclerEpisodesListAdapter.ListItemViewHolder> {
    private final List<VoEpisodes> list;
    private final Context ctx;
    private static RecyclerViewBaseClickListener listener;

    public static class ListItemViewHolder extends RecyclerView.ViewHolder {
        private final ItemListEpisodesBinding itemListEpisodesBinding;

        public ListItemViewHolder(@NonNull ItemListEpisodesBinding itemListEpisodesBinding) {
            super(itemListEpisodesBinding.getRoot());
            this.itemListEpisodesBinding = itemListEpisodesBinding;
        }
    }

    public RecyclerEpisodesListAdapter(RecyclerViewBaseClickListener l, Context ctx, List<VoEpisodes> list) {
        listener = l;
        this.ctx = ctx;
        this.list = list;
    }

    @NonNull
    @Override
    public ListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemListEpisodesBinding binding = ItemListEpisodesBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new RecyclerEpisodesListAdapter.ListItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemViewHolder holder, int position) {
        try {
            VoEpisodes episode = list.get(position);

            holder.itemListEpisodesBinding.episodesName.setText(episode.getName());
            holder.itemListEpisodesBinding.episodesAirDate.setText(episode.getAir_date());
            holder.itemListEpisodesBinding.episodesEpisode.setText(episode.getEpisode());

            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            try {
                Date date = format.parse(episode.getCreated());
                holder.itemListEpisodesBinding.episodesCreated.setText(date.toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            holder.itemListEpisodesBinding.itemListEpisodesBox.setOnClickListener(v -> listener.recyclerViewItemClicked(holder.getAdapterPosition()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}