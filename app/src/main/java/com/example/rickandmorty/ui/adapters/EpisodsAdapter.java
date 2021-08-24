package com.example.rickandmorty.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rickandmorty.databinding.EpisodsItemBinding;
import com.example.rickandmorty.models.episods.Episods;
import com.example.rickandmorty.utils.OnItemClickListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class EpisodsAdapter extends RecyclerView.Adapter<EpisodsAdapter.ViewHolder> {
    ArrayList<Episods> list = new ArrayList<>();


    OnItemClickListener onItemClickListener;

    public void setItemClickList(OnItemClickListener onclickListener) {
        this.onItemClickListener = onclickListener;
    }

    @NonNull
    @NotNull
    @Override
    public EpisodsAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new ViewHolder(EpisodsItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull EpisodsAdapter.ViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    public void addlist(ArrayList<Episods> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        EpisodsItemBinding binding;
        public ViewHolder(@NonNull @NotNull EpisodsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(Episods item) {
            binding.itemName.setText(item.getName());
            binding.itemAirDate.setText(item.getAir_date());
            binding.itemEpisode.setText(item.getEpisode());
            itemView.setOnClickListener(v ->
                    onItemClickListener.OnClickListener(item.getId()));
        }
    }
}