package com.example.test.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.databinding.ItemListLocationsBinding;
import com.example.test.interfaces.RecyclerViewBaseClickListener;
import com.example.test.models.locations.VoLocations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Victor
 */
public class RecyclerLocationsListAdapter extends RecyclerView.Adapter<RecyclerLocationsListAdapter.ListItemViewHolder> {
    private final List<VoLocations> list;
    private final Context ctx;
    private static RecyclerViewBaseClickListener listener;

    public static class ListItemViewHolder extends RecyclerView.ViewHolder {
        private final ItemListLocationsBinding itemListLocationsBinding;

        public ListItemViewHolder(@NonNull ItemListLocationsBinding itemListLocationsBinding) {
            super(itemListLocationsBinding.getRoot());
            this.itemListLocationsBinding = itemListLocationsBinding;
        }
    }

    public RecyclerLocationsListAdapter(RecyclerViewBaseClickListener l, Context ctx, List<VoLocations> list) {
        listener = l;
        this.ctx = ctx;
        this.list = list;
    }

    @NonNull
    @Override
    public ListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemListLocationsBinding binding = ItemListLocationsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new RecyclerLocationsListAdapter.ListItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemViewHolder holder, int position) {
        try {
            VoLocations location = list.get(position);

            holder.itemListLocationsBinding.locationName.setText(location.getName());
            holder.itemListLocationsBinding.locationType.setText(location.getType());
            holder.itemListLocationsBinding.locationDimension.setText(location.getDimension());

            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            try {
                Date date = format.parse(location.getCreated());
                holder.itemListLocationsBinding.characterCreated.setText(date.toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            holder.itemListLocationsBinding.itemListLocationBox.setOnClickListener(v -> listener.recyclerViewItemClicked(holder.getAdapterPosition()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}