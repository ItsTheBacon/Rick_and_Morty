package com.example.rickandmorty.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rickandmorty.databinding.CharterItemBinding;
import com.example.rickandmorty.models.charter.Character;
import com.example.rickandmorty.utils.OnItemClickListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.ViewHolder> {
    ArrayList<Character> list = new ArrayList<>();
    OnItemClickListener onItemClickListener;

    public void setItemClickList(OnItemClickListener onclickListener) {
        this.onItemClickListener = onclickListener;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new ViewHolder(CharterItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addlist(ArrayList<Character> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        CharterItemBinding binding;

        public ViewHolder(@NonNull @NotNull CharterItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(Character item) {
            binding.itemTitle.setText(item.getName());
            binding.itemStatus.setText(item.getStatus());
            binding.itemSpecies.setText(item.getSpecies());
            Glide.with(binding.itemImage)
                    .load(item.getImage())
                    .into(binding.itemImage);
            itemView.setOnClickListener(v ->
                    onItemClickListener.OnClickListener(item.getId()));
            itemView.setOnLongClickListener(v -> {
                onItemClickListener.OnLongListener(item.getId());
                return false;
            });
        }
    }
}
