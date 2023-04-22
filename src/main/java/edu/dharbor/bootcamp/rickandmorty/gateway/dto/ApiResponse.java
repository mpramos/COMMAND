package edu.dharbor.bootcamp.rickandmorty.gateway.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse {
    private Integer count;
    private String pages;
    private String next;
    private String prev;
}
