package edu.dharbor.bootcamp.rickandmorty.command.spec;

import edu.dharbor.bootcamp.rickandmorty.exception.CommandException;

public abstract class SafeAbstractCommand<I, O> implements Command<I, O> {
    public void safeExecute() throws CommandException {
        try {
            this.execute();
        } catch (Exception exception) {
            throw new CommandException(exception);
        }
    }
}
