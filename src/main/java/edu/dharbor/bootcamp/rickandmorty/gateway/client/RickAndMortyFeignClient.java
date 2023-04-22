package edu.dharbor.bootcamp.rickandmorty.gateway.client;

import edu.dharbor.bootcamp.rickandmorty.gateway.dto.CharacterApiResponse;
import edu.dharbor.bootcamp.rickandmorty.gateway.dto.CharacterListApiResponse;
import edu.dharbor.bootcamp.rickandmorty.util.constant.FeignClientConstant.CharacterApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = CharacterApi.CHARACTER_API_NAME, url = CharacterApi.CHARACTER_API_URL)
public interface RickAndMortyFeignClient {
    @GetMapping(value = "/?page={page}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    CharacterListApiResponse getCharacterList(@PathVariable("page") Integer page);

    @GetMapping(value = "/[{idList}]", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    List<CharacterApiResponse> getCharacterListById(@PathVariable("idList") List<Integer> idList);

    @GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    CharacterApiResponse getCharacterById(@PathVariable("id") Integer id);

    @GetMapping(
            value = "/?name={name}&status={status}&species={species}&type={type}&gender={gender}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    CharacterListApiResponse filterCharacterList(
            @PathVariable("name") String name,
            @PathVariable("status") String status,
            @PathVariable("species") String species,
            @PathVariable("type") String type,
            @PathVariable("gender") String gender
    );
}
