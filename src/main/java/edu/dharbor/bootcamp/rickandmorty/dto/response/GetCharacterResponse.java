package edu.dharbor.bootcamp.rickandmorty.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetCharacterResponse extends CommonResponse {
    private CharacterResponse character;
}
