package edu.dharbor.bootcamp.rickandmorty.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class GetCharacterRequest {
    @NotNull(message = "CHARACTER_ID must be provided")
    @Min(value = 1, message = "CHARACTER_ID number must be greater than 0")
    private Integer characterId;
}
