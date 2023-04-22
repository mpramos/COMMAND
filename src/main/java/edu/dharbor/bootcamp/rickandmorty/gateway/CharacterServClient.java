package edu.dharbor.bootcamp.rickandmorty.gateway;

import edu.dharbor.bootcamp.rickandmorty.gateway.dto.CharacterApiResponse;

import java.util.List;

public interface CharacterServClient {
    List<CharacterApiResponse> getCharacterListByPage(Integer page);

    List<CharacterApiResponse> getCharacterListByIdList(List<Integer> characterIdList);

    CharacterApiResponse getCharacterById(Integer characterId);
}
