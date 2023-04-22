package edu.dharbor.bootcamp.rickandmorty.gateway.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CharacterListApiResponse extends ApiResponse {
    private List<CharacterApiResponse> results;
}
