package com.example.rickandmorty.ui.fragments.episods;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rickandmorty.data.repositories.RickAndMortyRepository;
import com.example.rickandmorty.models.RickAndMoryResponse;
import com.example.rickandmorty.models.episods.Episods;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class EpisodsViewModel extends ViewModel {
    private final RickAndMortyRepository repository;
    public int page = 1;

    @Inject
    public EpisodsViewModel(RickAndMortyRepository repository) {
        this.repository = repository;
    }

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
