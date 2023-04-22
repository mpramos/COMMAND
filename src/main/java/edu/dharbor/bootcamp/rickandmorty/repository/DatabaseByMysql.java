package edu.dharbor.bootcamp.rickandmorty.repository;

import edu.dharbor.bootcamp.rickandmorty.mapper.CharacterMapper;
import edu.dharbor.bootcamp.rickandmorty.repository.dto.CharacterEntity;
import edu.dharbor.bootcamp.rickandmorty.repository.mysql.CharacterMysqlRepository;
import edu.dharbor.bootcamp.rickandmorty.repository.mysql.entity.CharacterMysqlEntity;
import edu.dharbor.bootcamp.rickandmorty.util.constant.CharacterGender;
import edu.dharbor.bootcamp.rickandmorty.util.constant.CharacterStatus;
import edu.dharbor.bootcamp.rickandmorty.util.constant.DatabaseStrategyName;
import edu.dharbor.bootcamp.rickandmorty.util.function.Utils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static edu.dharbor.bootcamp.rickandmorty.util.constant.CommonConstant.STRING_EMPTY;

@Service
@AllArgsConstructor
public class DatabaseByMysql implements DatabaseStrategy {
    private final CharacterMysqlRepository characterMysqlRepository;
    private final CharacterMapper characterMapper;

    @Override
    public DatabaseStrategyName getStrategyName() {
        return DatabaseStrategyName.MYSQL;
    }

    @Override
    public CharacterEntity getCharacterById(Integer characterId) {
        Optional<CharacterMysqlEntity> characterMysqlEntity = characterMysqlRepository.findByRegistrationId(characterId);
        return characterMapper.mapperToCharacterEntityFrom(characterMysqlEntity.orElse(new CharacterMysqlEntity()));
    }

    @Override
    public List<CharacterEntity> getCharacterBy(String name, CharacterStatus status, String species, CharacterGender gender) {
        List<CharacterMysqlEntity> characterMysqlEntityList = characterMysqlRepository.findBy(
                Utils.nvl(name, STRING_EMPTY),
                Utils.nvl(status, STRING_EMPTY).toString(),
                Utils.nvl(species, STRING_EMPTY),
                Utils.nvl(gender, STRING_EMPTY).toString()
        );
        return characterMapper.mapperToCharacterEntityListFromCharacterMysqlEntityList(characterMysqlEntityList);
    }

    @Override
    public void save(CharacterEntity characterEntity) {
        CharacterMysqlEntity characterMysqlEntity = characterMapper.mapperToCharacterMysqlEntityFrom(characterEntity);
        characterMysqlRepository.save(characterMysqlEntity);
    }
}
