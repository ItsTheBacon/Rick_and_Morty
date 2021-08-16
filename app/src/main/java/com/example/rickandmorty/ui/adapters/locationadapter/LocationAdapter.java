package com.example.rickandmorty.ui.adapters.locationadapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rickandmorty.databinding.LocationItemBinding;
import com.example.rickandmorty.models.location.Location;
import com.example.rickandmorty.utils.OnItemClickListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder> {
    ArrayList<Location> list = new ArrayList<>();

    LocationItemBinding binding;
    OnItemClickListener onItemClickListener;

    public void setItemClickList(OnItemClickListener onclickListener) {
        this.onItemClickListener = onclickListener;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        binding = LocationItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public void addlist(ArrayList<Location> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

        }

        public void onBind(Location item) {
            binding.itemName.setText(item.getName());
            binding.itemPlanet.setText(item.getType());
            binding.itemDimension.setText(item.getDimension());
            itemView.setOnClickListener(v ->
                    onItemClickListener.OnClickListener(item.getId()));

        }
    }
}
