package edu.dharbor.bootcamp.rickandmorty.gateway.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CharacterApiResponse {
    private Integer id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    private OriginApiResponse origin;
    private LocationApiResponse location;
    private String image;
    private List<String> episode;
    private String url;
    private String created;
}
