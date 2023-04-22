package edu.dharbor.bootcamp.rickandmorty.service;

import edu.dharbor.bootcamp.rickandmorty.dto.response.CharacterResponse;
import edu.dharbor.bootcamp.rickandmorty.gateway.CharacterServClient;
import edu.dharbor.bootcamp.rickandmorty.gateway.dto.CharacterApiResponse;
import edu.dharbor.bootcamp.rickandmorty.mapper.CharacterMapper;
import edu.dharbor.bootcamp.rickandmorty.repository.DatabaseStrategy;
import edu.dharbor.bootcamp.rickandmorty.repository.DatabaseStrategyFactory;
import edu.dharbor.bootcamp.rickandmorty.repository.dto.CharacterEntity;
import edu.dharbor.bootcamp.rickandmorty.util.constant.CharacterGender;
import edu.dharbor.bootcamp.rickandmorty.util.constant.CharacterStatus;
import edu.dharbor.bootcamp.rickandmorty.util.function.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CharacterServiceImpl implements CharacterService {
    private final CharacterServClient characterServClient;
    private final CharacterMapper characterMapper;
    private final DatabaseStrategyFactory databaseStrategyFactory;
    @Value("${application.databaseUsed}")
    private String databaseUsed;

    @Autowired
    public CharacterServiceImpl(CharacterServClient characterServClient, CharacterMapper characterMapper, DatabaseStrategyFactory databaseStrategyFactory) {
        this.characterServClient = characterServClient;
        this.characterMapper = characterMapper;
        this.databaseStrategyFactory = databaseStrategyFactory;
    }

    @Override
    public List<CharacterResponse> getCharacterListFromDatabase(List<Integer> characterIdList) {
        DatabaseStrategy strategy = databaseStrategyFactory.getStrategy(this.databaseUsed);
        return characterIdList.stream()
                .map(strategy::getCharacterById)
                .filter(characterEntity -> !Utils.isNull(characterEntity.getId()))
                .map(characterMapper::mapperToCharacterResponseFrom)
                .collect(Collectors.toList());
    }

    @Override
    public List<CharacterResponse> getCharacterListFromApi(List<Integer> characterIdList) {
        return characterServClient.getCharacterListByIdList(characterIdList)
                .stream()
                .map(characterMapper::mapperToCharacterResponseFrom)
                .collect(Collectors.toList());
    }

    @Override
    public CharacterResponse getCharacterFromDatabase(Integer characterId) {
        DatabaseStrategy strategy = databaseStrategyFactory.getStrategy(this.databaseUsed);
        CharacterEntity characterEntity = strategy.getCharacterById(characterId);
        return characterMapper.mapperToCharacterResponseFrom(characterEntity);
    }

    @Override
    public CharacterResponse getCharacterFromApi(Integer characterId) {
        DatabaseStrategy strategy = databaseStrategyFactory.getStrategy(this.databaseUsed);
        CharacterApiResponse characterApiResponse = characterServClient.getCharacterById(characterId);
        return characterMapper.mapperToCharacterResponseFrom(characterApiResponse);
    }

    @Override
    public List<CharacterResponse> findCharacterBy(String name, CharacterStatus status, String species, CharacterGender gender) {
        DatabaseStrategy strategy = databaseStrategyFactory.getStrategy(this.databaseUsed);
        List<CharacterEntity> characterEntityList = strategy.getCharacterBy(name, status, species, gender);
        return characterEntityList.stream()
                .map(characterMapper::mapperToCharacterResponseFrom)
                .collect(Collectors.toList());
    }

    @Override
    public void saveCharacter(CharacterResponse characterResponse) {
        DatabaseStrategy strategy = databaseStrategyFactory.getStrategy(this.databaseUsed);
        CharacterEntity characterEntity = strategy.getCharacterById(characterResponse.getCharacterId());
        if (Utils.isNull(characterEntity.getId())) {
            characterEntity = characterMapper.mapperToCharacterEntityFrom(characterResponse);
            strategy.save(characterEntity);
        }
    }
}
