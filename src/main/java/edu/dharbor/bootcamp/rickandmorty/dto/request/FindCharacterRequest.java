package edu.dharbor.bootcamp.rickandmorty.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
public class FindCharacterRequest {
    @Size(min = 1, max = 50, message = "NAME must be between 1 and 50 characters")
    private String name;
    private String status;
    @Size(min = 1, max = 20, message = "SPECIES must be between 1 and 20 characters")
    private String species;
    private String gender;
}
