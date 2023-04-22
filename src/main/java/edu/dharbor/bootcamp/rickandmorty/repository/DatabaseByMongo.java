package edu.dharbor.bootcamp.rickandmorty.repository;

import edu.dharbor.bootcamp.rickandmorty.mapper.CharacterMapper;
import edu.dharbor.bootcamp.rickandmorty.repository.dto.CharacterEntity;
import edu.dharbor.bootcamp.rickandmorty.repository.mongodb.CharacterMongoRepository;
import edu.dharbor.bootcamp.rickandmorty.repository.mongodb.document.CharacterMongoDocument;
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
public class DatabaseByMongo implements DatabaseStrategy {
    private final CharacterMongoRepository characterMongoRepository;
    private final CharacterMapper characterMapper;

    @Override
    public DatabaseStrategyName getStrategyName() {
        return DatabaseStrategyName.MONGODB;
    }

    @Override
    public CharacterEntity getCharacterById(Integer id) {
        Optional<CharacterMongoDocument> characterMongoDocument = characterMongoRepository.findByRegistrationId(id);
        return characterMapper.mapperToCharacterEntityFrom(characterMongoDocument.orElse(new CharacterMongoDocument()));
    }

    @Override
    public List<CharacterEntity> getCharacterBy(String name, CharacterStatus status, String species, CharacterGender gender) {
        List<CharacterMongoDocument> characterMongoDocument = characterMongoRepository.findBy(
                Utils.nvl(name, STRING_EMPTY),
                Utils.nvl(status, STRING_EMPTY).toString(),
                Utils.nvl(species, STRING_EMPTY),
                Utils.nvl(gender, STRING_EMPTY).toString()
        );
        return characterMapper.mapperToCharacterEntityListFromCharacterMongoDocumentList(characterMongoDocument);
    }

    @Override
    public void save(CharacterEntity characterEntity) {
        CharacterMongoDocument characterMongoDocument = characterMapper.mapperToCharacterMongoDocumentFrom(characterEntity);
        characterMongoRepository.save(characterMongoDocument);
    }
}
