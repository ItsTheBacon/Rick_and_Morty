package com.example.rickandmorty.ui.fragments.character;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rickandmorty.data.repositories.RickAndMortyRepository;
import com.example.rickandmorty.models.RickAndMoryResponse;
import com.example.rickandmorty.models.charter.Character;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class CharterViewModel extends ViewModel {
    private final RickAndMortyRepository repository;

    public int page = 1;

    @Inject
    public CharterViewModel(RickAndMortyRepository rickAndMortyRepository){
        this.repository = rickAndMortyRepository;
    }


    MutableLiveData<RickAndMoryResponse<Character>> fetchCharacters() {
        return repository.fetchCharacters(page);
    }

    public MutableLiveData<Character> fetchCharacter(int id) {
        return repository.fetchCharacter(id);
    }

    public List<Character> getCharacters() {
        return repository.getCharacters();
    }

}
