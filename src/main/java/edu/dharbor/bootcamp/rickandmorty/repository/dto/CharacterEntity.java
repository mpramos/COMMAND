package edu.dharbor.bootcamp.rickandmorty.repository.dto;

import edu.dharbor.bootcamp.rickandmorty.util.constant.CharacterGender;
import edu.dharbor.bootcamp.rickandmorty.util.constant.CharacterStatus;
import lombok.Data;

import java.util.Date;

@Data
public class CharacterEntity {
    private Integer id;
    private String name;
    private CharacterStatus status;
    private String species;
    private String type;
    private CharacterGender gender;
    private String url;
    private Date created;
}
