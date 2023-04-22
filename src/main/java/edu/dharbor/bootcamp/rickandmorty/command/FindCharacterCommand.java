package edu.dharbor.bootcamp.rickandmorty.command;

import edu.dharbor.bootcamp.rickandmorty.command.spec.PostExecutorCommand;
import edu.dharbor.bootcamp.rickandmorty.command.spec.PreExecutorCommand;
import edu.dharbor.bootcamp.rickandmorty.command.spec.SafeAbstractCommand;
import edu.dharbor.bootcamp.rickandmorty.dto.request.FindCharacterRequest;
import edu.dharbor.bootcamp.rickandmorty.dto.response.CharacterResponse;
import edu.dharbor.bootcamp.rickandmorty.dto.response.FindCharacterResponse;
import edu.dharbor.bootcamp.rickandmorty.exception.ProcessErrorException;
import edu.dharbor.bootcamp.rickandmorty.mapper.CharacterMapper;
import edu.dharbor.bootcamp.rickandmorty.service.CharacterService;
import edu.dharbor.bootcamp.rickandmorty.util.constant.CharacterGender;
import edu.dharbor.bootcamp.rickandmorty.util.constant.CharacterStatus;
import edu.dharbor.bootcamp.rickandmorty.util.function.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class FindCharacterCommand
        extends SafeAbstractCommand<FindCharacterRequest, FindCharacterResponse>
        implements PreExecutorCommand, PostExecutorCommand {
    private final CharacterService characterService;
    private final CharacterMapper characterMapper;
    private FindCharacterRequest input;
    private FindCharacterResponse output;

    @Autowired
    public FindCharacterCommand(CharacterService characterService, CharacterMapper characterMapper) {
        this.characterService = characterService;
        this.characterMapper = characterMapper;
    }

    @Override
    public void setInput(FindCharacterRequest input) {
        this.input = input;
    }

    @Override
    public FindCharacterResponse getOutput() {
        return output;
    }

    @Override
    public void execute() {
        log.info("FindCharacterCommand - Execute");
        String filterByName = this.input.getName();
        CharacterStatus filterByStatus = Utils.isNull(this.input.getStatus()) ? null : CharacterStatus.valueOf(this.input.getStatus().toUpperCase());
        String filterBySpecies = this.input.getSpecies();
        CharacterGender filterByGender = Utils.isNull(this.input.getGender()) ? null : CharacterGender.valueOf(this.input.getGender().toUpperCase());
        List<CharacterResponse> characterResponseList = characterService.findCharacterBy(filterByName, filterByStatus, filterBySpecies, filterByGender);
        this.output = characterMapper.mapperToFindCharacterResponseFrom(characterResponseList);
    }

    @Override
    public void preExecute() {
        log.info("FindCharacterCommand - PreExecute");
        if (!Utils.isNull(this.input.getGender())) {
            Arrays.stream(CharacterGender.values())
                    .filter(characterGender -> characterGender.name().equalsIgnoreCase(this.input.getGender()))
                    .findAny()
                    .orElseThrow(() -> new ProcessErrorException("GENDER is not valid"));
        }
        if (!Utils.isNull(this.input.getStatus())) {
            boolean isStatusValid = Arrays.stream(CharacterStatus.values())
                    .anyMatch(characterStatus ->
                            characterStatus.name().equalsIgnoreCase(this.input.getStatus().toUpperCase())
                    );
            if (!isStatusValid) {
                throw new ProcessErrorException("STATUS is not valid");
            }
        }
    }

    @Override
    public void postExecute() {
        log.info("FindCharacterCommand - PostExecute");
        if (Utils.isNull(this.output.getCharacterResponseList())) {
            this.output.setCharacterResponseList(Collections.emptyList());
        }
    }
}
