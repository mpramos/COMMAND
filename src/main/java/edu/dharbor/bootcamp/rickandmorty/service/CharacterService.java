package edu.dharbor.bootcamp.rickandmorty.service;

import edu.dharbor.bootcamp.rickandmorty.dto.response.CharacterResponse;
import edu.dharbor.bootcamp.rickandmorty.util.constant.CharacterGender;
import edu.dharbor.bootcamp.rickandmorty.util.constant.CharacterStatus;

import java.util.List;

public interface CharacterService {
    List<CharacterResponse> getCharacterListFromDatabase(List<Integer> characterIdList);

    List<CharacterResponse> getCharacterListFromApi(List<Integer> characterIdList);

    CharacterResponse getCharacterFromDatabase(Integer characterId);

    CharacterResponse getCharacterFromApi(Integer characterId);

    List<CharacterResponse> findCharacterBy(String name, CharacterStatus status, String species, CharacterGender gender);

    void saveCharacter(CharacterResponse characterResponse);
}
