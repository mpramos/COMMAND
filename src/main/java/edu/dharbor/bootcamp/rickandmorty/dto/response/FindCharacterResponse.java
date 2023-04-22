package edu.dharbor.bootcamp.rickandmorty.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FindCharacterResponse extends CommonResponse {
    List<CharacterResponse> characterResponseList;
}
