package edu.dharbor.bootcamp.rickandmorty.controller;

import edu.dharbor.bootcamp.rickandmorty.command.FindCharacterCommand;
import edu.dharbor.bootcamp.rickandmorty.command.GetAllCharactersCommand;
import edu.dharbor.bootcamp.rickandmorty.command.GetCharacterCommand;
import edu.dharbor.bootcamp.rickandmorty.command.spec.SafeCommandExecutor;
import edu.dharbor.bootcamp.rickandmorty.dto.request.FindCharacterRequest;
import edu.dharbor.bootcamp.rickandmorty.dto.request.GetCharacterListByPageRequest;
import edu.dharbor.bootcamp.rickandmorty.dto.request.GetCharacterRequest;
import edu.dharbor.bootcamp.rickandmorty.dto.response.FindCharacterResponse;
import edu.dharbor.bootcamp.rickandmorty.dto.response.GetCharacterListByPageResponse;
import edu.dharbor.bootcamp.rickandmorty.dto.response.GetCharacterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public final class CharacterController {
    private final GetAllCharactersCommand getAllCharactersCommand;
    private final GetCharacterCommand getCharacterCommand;
    private final FindCharacterCommand findCharacterCommand;

    @PostMapping(value = "/GetCharacterListByPage", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public GetCharacterListByPageResponse getCharacterListByPage(@Valid @RequestBody GetCharacterListByPageRequest request) {
        getAllCharactersCommand.setInput(request);
        (new SafeCommandExecutor<GetCharacterListByPageRequest, GetCharacterListByPageResponse>()).safeExecution(getAllCharactersCommand);
        return getAllCharactersCommand.getOutput();
    }

    @PostMapping(value = "/GetCharacter", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public GetCharacterResponse getCharacter(@Valid @RequestBody GetCharacterRequest request) {
        getCharacterCommand.setInput(request);
        (new SafeCommandExecutor<GetCharacterRequest, GetCharacterResponse>()).safeExecution(getCharacterCommand);
        return getCharacterCommand.getOutput();
    }

    @PostMapping(value = "/FindCharacter", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public FindCharacterResponse findCharacter(@Valid @RequestBody FindCharacterRequest request) {
        findCharacterCommand.setInput(request);
        (new SafeCommandExecutor<FindCharacterRequest, FindCharacterResponse>()).safeExecution(findCharacterCommand);
        return findCharacterCommand.getOutput();
    }
}
