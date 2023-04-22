package edu.dharbor.bootcamp.rickandmorty.mapper;

import edu.dharbor.bootcamp.rickandmorty.dto.response.CharacterResponse;
import edu.dharbor.bootcamp.rickandmorty.dto.response.FindCharacterResponse;
import edu.dharbor.bootcamp.rickandmorty.dto.response.GetCharacterListByPageResponse;
import edu.dharbor.bootcamp.rickandmorty.dto.response.GetCharacterResponse;
import edu.dharbor.bootcamp.rickandmorty.gateway.dto.CharacterApiResponse;
import edu.dharbor.bootcamp.rickandmorty.repository.dto.CharacterEntity;
import edu.dharbor.bootcamp.rickandmorty.repository.mongodb.document.CharacterMongoDocument;
import edu.dharbor.bootcamp.rickandmorty.repository.mysql.entity.CharacterMysqlEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

import static edu.dharbor.bootcamp.rickandmorty.util.constant.ResponseConstant.SuccessResponse;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, imports = {SuccessResponse.class})
public interface CharacterMapper {
    @Mapping(source = "id", target = "characterId")
    @Mapping(source = "name", target = "name")
    @Mapping(expression = "java(source.getStatus().toUpperCase())", target = "status")
    @Mapping(source = "species", target = "species")
    @Mapping(source = "type", target = "type")
    @Mapping(expression = "java(source.getGender().toUpperCase())", target = "gender")
    @Mapping(source = "image", target = "image")
    @Mapping(source = "url", target = "url")
    @Mapping(source = "created", target = "created", dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    CharacterResponse mapperToCharacterResponseFrom(CharacterApiResponse source);

    @Mapping(source = "id", target = "characterId")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "species", target = "species")
    @Mapping(source = "type", target = "type")
    @Mapping(source = "gender", target = "gender")
    @Mapping(source = "url", target = "url")
    CharacterResponse mapperToCharacterResponseFrom(CharacterEntity source);

    default GetCharacterListByPageResponse mapperToGetCharacterListByPageResponseFrom(List<CharacterResponse> sourceList) {
        return toGetCharacterListByPageResponse(SuccessResponse.CODE, SuccessResponse.MESSAGE, sourceList);
    }

    GetCharacterListByPageResponse toGetCharacterListByPageResponse(String code, String message, List<CharacterResponse> resultList);

    @Mapping(source = "characterId", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "species", target = "species")
    @Mapping(source = "type", target = "type")
    @Mapping(source = "gender", target = "gender")
    @Mapping(source = "url", target = "url")
    CharacterEntity mapperToCharacterEntityFrom(CharacterResponse source);

    @Mapping(source = "registrationId", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "species", target = "species")
    @Mapping(source = "type", target = "type")
    @Mapping(source = "gender", target = "gender")
    @Mapping(source = "url", target = "url")
    @Mapping(source = "created", target = "created")
    CharacterEntity mapperToCharacterEntityFrom(CharacterMysqlEntity source);

    List<CharacterEntity> mapperToCharacterEntityListFromCharacterMysqlEntityList(List<CharacterMysqlEntity> sourceList);

    @Mapping(source = "registrationId", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "species", target = "species")
    @Mapping(source = "type", target = "type")
    @Mapping(source = "gender", target = "gender")
    @Mapping(source = "url", target = "url")
    @Mapping(source = "created", target = "created")
    CharacterEntity mapperToCharacterEntityFrom(CharacterMongoDocument source);

    List<CharacterEntity> mapperToCharacterEntityListFromCharacterMongoDocumentList(List<CharacterMongoDocument> sourceList);

    @Mapping(source = "id", target = "registrationId")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "species", target = "species")
    @Mapping(source = "type", target = "type")
    @Mapping(source = "gender", target = "gender")
    @Mapping(source = "url", target = "url")
    @Mapping(source = "created", target = "created")
    CharacterMysqlEntity mapperToCharacterMysqlEntityFrom(CharacterEntity source);

    @Mapping(source = "id", target = "registrationId")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "species", target = "species")
    @Mapping(source = "type", target = "type")
    @Mapping(source = "gender", target = "gender")
    @Mapping(source = "url", target = "url")
    @Mapping(source = "created", target = "created")
    CharacterMongoDocument mapperToCharacterMongoDocumentFrom(CharacterEntity source);

    @Mapping(expression = "java(SuccessResponse.CODE)", target = "code")
    @Mapping(expression = "java(SuccessResponse.MESSAGE)", target = "message")
    @Mapping(source = "source", target = "character")
    GetCharacterResponse mapperToGetCharacterResponseFrom(CharacterResponse source);

    default FindCharacterResponse mapperToFindCharacterResponseFrom(List<CharacterResponse> sourceList) {
        return toFindCharacterResponse(SuccessResponse.CODE, SuccessResponse.MESSAGE, sourceList);
    }

    FindCharacterResponse toFindCharacterResponse(String code, String message, List<CharacterResponse> resultList);
}
