package com.example.rickandmorty.ui.fragments.episods;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rickandmorty.data.repositories.RickAndMortyRepository;
import com.example.rickandmorty.models.RickAndMoryResponse;
import com.example.rickandmorty.models.episods.Episods;

import java.util.List;

public class EpisodsViewModel extends ViewModel {
    private final RickAndMortyRepository repository = new RickAndMortyRepository();
    public int page = 1;

    MutableLiveData<RickAndMoryResponse<Episods>> fetchEpisods() {
        return repository.fetchEpisods(page);
    }

    public MutableLiveData<Episods> fetchId(int id) {
        return repository.fetchEpisodsdetail(id);
    }

    public List<Episods> getEpisods() {
        return repository.getEpisods();
    }

}
