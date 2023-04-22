package edu.dharbor.bootcamp.rickandmorty.command.spec;

public interface Command<I, O> {
    void setInput(I input);

    O getOutput();

    void execute();
}
