package edu.dharbor.bootcamp.rickandmorty.gateway;

import edu.dharbor.bootcamp.rickandmorty.gateway.client.RickAndMortyFeignClient;
import edu.dharbor.bootcamp.rickandmorty.gateway.dto.CharacterApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CharacterServClientImpl implements CharacterServClient {
    private final RickAndMortyFeignClient rickAndMortyFeignClient;

    @Override
    public List<CharacterApiResponse> getCharacterListByPage(Integer page) {
        return rickAndMortyFeignClient.getCharacterList(page).getResults();
    }

    @Override
    public List<CharacterApiResponse> getCharacterListByIdList(List<Integer> characterIdList) {
        if (characterIdList.isEmpty()) {
            return new ArrayList<>();
        }
        return rickAndMortyFeignClient.getCharacterListById(characterIdList);
    }

    @Override
    public CharacterApiResponse getCharacterById(Integer characterId) {
        return rickAndMortyFeignClient.getCharacterById(characterId);
    }
}
