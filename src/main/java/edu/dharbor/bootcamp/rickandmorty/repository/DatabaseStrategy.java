package edu.dharbor.bootcamp.rickandmorty.repository;

import edu.dharbor.bootcamp.rickandmorty.repository.dto.CharacterEntity;
import edu.dharbor.bootcamp.rickandmorty.util.constant.CharacterGender;
import edu.dharbor.bootcamp.rickandmorty.util.constant.CharacterStatus;
import edu.dharbor.bootcamp.rickandmorty.util.constant.DatabaseStrategyName;

import java.util.List;

public interface DatabaseStrategy {
    DatabaseStrategyName getStrategyName();

    CharacterEntity getCharacterById(Integer id);

    List<CharacterEntity> getCharacterBy(String name, CharacterStatus status, String species, CharacterGender gender);

    void save(CharacterEntity characterEntity);
}
