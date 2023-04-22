package edu.dharbor.bootcamp.rickandmorty.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;

@Setter
@Getter
public class GetCharacterListByPageRequest {
    @Min(value = 1, message = "PAGE number must be greater than 0")
    private Integer page;
}
