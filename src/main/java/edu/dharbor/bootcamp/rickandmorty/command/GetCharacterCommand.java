package edu.dharbor.bootcamp.rickandmorty.command;

import edu.dharbor.bootcamp.rickandmorty.command.spec.PostExecutorCommand;
import edu.dharbor.bootcamp.rickandmorty.command.spec.PreExecutorCommand;
import edu.dharbor.bootcamp.rickandmorty.command.spec.SafeAbstractCommand;
import edu.dharbor.bootcamp.rickandmorty.dto.request.GetCharacterRequest;
import edu.dharbor.bootcamp.rickandmorty.dto.response.CharacterResponse;
import edu.dharbor.bootcamp.rickandmorty.dto.response.GetCharacterResponse;
import edu.dharbor.bootcamp.rickandmorty.mapper.CharacterMapper;
import edu.dharbor.bootcamp.rickandmorty.service.CharacterService;
import edu.dharbor.bootcamp.rickandmorty.util.function.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GetCharacterCommand
        extends SafeAbstractCommand<GetCharacterRequest, GetCharacterResponse>
        implements PreExecutorCommand, PostExecutorCommand {
    private final CharacterMapper characterMapper;
    private final CharacterService characterService;
    private GetCharacterRequest input;
    private GetCharacterResponse output;

    @Autowired
    public GetCharacterCommand(CharacterMapper characterMapper, CharacterService characterService) {
        this.characterMapper = characterMapper;
        this.characterService = characterService;
    }

    @Override
    public void setInput(GetCharacterRequest input) {
        this.input = input;
    }

    @Override
    public GetCharacterResponse getOutput() {
        return output;
    }

    @Override
    public void execute() {
        log.info("GetCharacterCommand - Execute");
        CharacterResponse characterResponse = characterService.getCharacterFromDatabase(this.input.getCharacterId());
        if (Utils.isNull(characterResponse.getCharacterId())) {
            characterResponse = this.getCharacterFromApi(this.input.getCharacterId());
        }
        this.output = characterMapper.mapperToGetCharacterResponseFrom(characterResponse);
    }

    private CharacterResponse getCharacterFromApi(Integer characterId) {
        CharacterResponse characterResponse = characterService.getCharacterFromApi(characterId);
        characterService.saveCharacter(characterResponse);
        return characterResponse;
    }

    @Override
    public void preExecute() {
        log.info("GetCharacterCommand - PreExecute");
    }

    @Override
    public void postExecute() {
        log.info("GetCharacterCommand - PostExecute");
        if (Utils.isNull(this.output.getCharacter())) {
            this.output.setCharacter(new CharacterResponse());
        }
    }
}
