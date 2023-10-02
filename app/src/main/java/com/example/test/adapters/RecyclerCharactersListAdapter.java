package com.example.test.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.databinding.ItemListCharactersBinding;
import com.example.test.interfaces.RecyclerViewBaseClickListener;
import com.example.test.models.characters.VoCharacters;

import java.io.InputStream;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Victor
 */
public class RecyclerCharactersListAdapter extends RecyclerView.Adapter<RecyclerCharactersListAdapter.ListItemViewHolder> {
    private final List<VoCharacters> list;
    private final Context ctx;
    private static RecyclerViewBaseClickListener listener;

    public static class ListItemViewHolder extends RecyclerView.ViewHolder {
        private final ItemListCharactersBinding itemListCharactersBinding;

        public ListItemViewHolder(@NonNull ItemListCharactersBinding itemListCharactersBinding) {
            super(itemListCharactersBinding.getRoot());
            this.itemListCharactersBinding = itemListCharactersBinding;
        }
    }

    public RecyclerCharactersListAdapter(RecyclerViewBaseClickListener l, Context ctx, List<VoCharacters> list) {
        listener = l;
        this.ctx = ctx;
        this.list = list;
    }

    @NonNull
    @Override
    public ListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemListCharactersBinding binding = ItemListCharactersBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new RecyclerCharactersListAdapter.ListItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ListItemViewHolder holder, int position) {
        try {
            VoCharacters character = list.get(position);

            InputStream is = (InputStream) new URL(character.getImage()).getContent();
            Drawable d = Drawable.createFromStream(is, "");

            if (character.getStatus().equals("Alive")) {
                holder.itemListCharactersBinding.characterStatus.setTextColor(Color.parseColor("#619061"));
            } else if (character.getStatus().equals("Dead")) {
                holder.itemListCharactersBinding.characterStatus.setTextColor(Color.parseColor("#B26060"));
            } else {
                holder.itemListCharactersBinding.characterStatus.setTextColor(Color.parseColor("#DACF4A"));
            }

            String infoDetails = character.getSpecies() + " - " + character.getGender();
            holder.itemListCharactersBinding.characterStatus.setText(infoDetails);

            holder.itemListCharactersBinding.characterImage.setImageDrawable(d);
            holder.itemListCharactersBinding.characterName.setText(character.getName());

            /*
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            try {
                //Date date = format.parse(character.getCreated());
                //holder.itemListCharactersBinding.characterCreated.setText(date.toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }*/

            holder.itemListCharactersBinding.itemListCharacterBox.setOnClickListener(v -> listener.recyclerViewItemClicked(holder.getAdapterPosition()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}