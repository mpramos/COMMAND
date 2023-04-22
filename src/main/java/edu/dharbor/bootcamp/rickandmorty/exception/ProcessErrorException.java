package edu.dharbor.bootcamp.rickandmorty.exception;

public class ProcessErrorException extends RuntimeException {
    public ProcessErrorException(String message) {
        super(message);
    }

    public ProcessErrorException(Throwable cause) {
        super(cause);
    }

    public ProcessErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
