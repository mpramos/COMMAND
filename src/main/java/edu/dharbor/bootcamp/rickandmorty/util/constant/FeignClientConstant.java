package edu.dharbor.bootcamp.rickandmorty.util.constant;

public final class FeignClientConstant {
    public static final String RICK_AND_MORTY_API_CLIENT = "${application.gateway.rickAndMortyApi}";

    public static final class CharacterApi {
        public static final String CHARACTER_API_NAME = "RickAndMortyApiClient";
        public static final String CHARACTER_API_URL = RICK_AND_MORTY_API_CLIENT + "/character";
    }
}
