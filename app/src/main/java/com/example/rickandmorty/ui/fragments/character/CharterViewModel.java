package com.example.rickandmorty.ui.fragments.character;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rickandmorty.data.repositories.RickAndMortyRepository;
import com.example.rickandmorty.models.RickAndMoryResponse;
import com.example.rickandmorty.models.charter.Character;

import java.util.List;

public class CharterViewModel extends ViewModel {
    private final RickAndMortyRepository repository = new RickAndMortyRepository();

    MutableLiveData<RickAndMoryResponse<Character>> fetchCharacters() {
        return repository.fetchCharacters();
    }

    public MutableLiveData<Character> fetchCharacter(int id) {
        return repository.fetchCharacter(id);
    }

    public List<Character> getCharacters() {
        return repository.getCharacters();
    }

}
