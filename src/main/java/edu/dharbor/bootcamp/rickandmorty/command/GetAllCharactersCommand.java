package edu.dharbor.bootcamp.rickandmorty.command;

import edu.dharbor.bootcamp.rickandmorty.command.spec.PostExecutorCommand;
import edu.dharbor.bootcamp.rickandmorty.command.spec.PreExecutorCommand;
import edu.dharbor.bootcamp.rickandmorty.command.spec.SafeAbstractCommand;
import edu.dharbor.bootcamp.rickandmorty.dto.request.GetCharacterListByPageRequest;
import edu.dharbor.bootcamp.rickandmorty.dto.response.CharacterResponse;
import edu.dharbor.bootcamp.rickandmorty.dto.response.GetCharacterListByPageResponse;
import edu.dharbor.bootcamp.rickandmorty.mapper.CharacterMapper;
import edu.dharbor.bootcamp.rickandmorty.service.CharacterService;
import edu.dharbor.bootcamp.rickandmorty.util.function.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static edu.dharbor.bootcamp.rickandmorty.util.constant.CommonConstant.AMOUNT_MAX_BY_PAGE;
import static edu.dharbor.bootcamp.rickandmorty.util.constant.CommonConstant.DEFAULT_PAGE;

@Slf4j
@Service
public class GetAllCharactersCommand
        extends SafeAbstractCommand<GetCharacterListByPageRequest, GetCharacterListByPageResponse>
        implements PreExecutorCommand, PostExecutorCommand {
    private final CharacterService characterService;
    private final CharacterMapper characterMapper;
    private GetCharacterListByPageRequest input;
    private GetCharacterListByPageResponse output;

    @Autowired
    public GetAllCharactersCommand(CharacterService characterService, CharacterMapper characterMapper) {
        this.characterService = characterService;
        this.characterMapper = characterMapper;
    }

    @Override
    public void setInput(GetCharacterListByPageRequest input) {
        this.input = input;
    }

    @Override
    public GetCharacterListByPageResponse getOutput() {
        return this.output;
    }

    @Override
    public void execute() {
        log.info("GetAllCharactersCommand - Execute");
        List<Integer> characterIdList = this.generateCharacterIdListByPage(this.input.getPage());
        List<CharacterResponse> characterListFromDatabase = characterService.getCharacterListFromDatabase(characterIdList);
        List<Integer> characterIdNotSavedList = this.filterCharacterIdList(characterIdList, characterListFromDatabase);
        List<CharacterResponse> characterListFromApi = characterService.getCharacterListFromApi(characterIdNotSavedList);
        List<CharacterResponse> characterResponseList = this.joinResponseApiAndDatabase(
                characterListFromDatabase,
                characterListFromApi
        );
        characterResponseList.forEach(characterService::saveCharacter);
        this.output = characterMapper.mapperToGetCharacterListByPageResponseFrom(characterResponseList);
    }

    private List<CharacterResponse> joinResponseApiAndDatabase(
            List<CharacterResponse> characterListFromDatabase,
            List<CharacterResponse> characterListFromApi
    ) {
        return Stream.concat(characterListFromDatabase.stream(), characterListFromApi.stream())
                .sorted(Comparator.comparingInt(CharacterResponse::getCharacterId))
                .collect(Collectors.toList());
    }

    private List<Integer> filterCharacterIdList(List<Integer> characterIdList, List<CharacterResponse> characterEntityList) {
        return characterIdList.stream()
                .filter(characterId -> characterEntityList.stream().noneMatch(characterEntity -> characterEntity.getCharacterId().equals(characterId)))
                .collect(Collectors.toList());
    }

    private List<Integer> generateCharacterIdListByPage(Integer page) {
        int pageNumber = Utils.isNull(page) ? DEFAULT_PAGE : page;
        int offset = ((pageNumber - 1) * AMOUNT_MAX_BY_PAGE);
        int limit = (pageNumber * AMOUNT_MAX_BY_PAGE);
        return IntStream.range(offset + 1, limit + 1).boxed().collect(Collectors.toList());
    }

    @Override
    public void preExecute() {
        log.info("GetAllCharactersCommand - PreExecute");
    }

    @Override
    public void postExecute() {
        log.info("GetAllCharactersCommand - PostExecute");
        if (Utils.isNull(this.output.getResultList())) {
            this.output.setResultList(Collections.emptyList());
        }
    }
}
