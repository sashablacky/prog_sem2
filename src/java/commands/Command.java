package commands;

import exceptions.CommandException;
import exceptions.InvalidDataException;
@FunctionalInterface
/**
 * Command callback interface
 */
public interface Command {
    void run(String arg) throws CommandException, InvalidDataException;
}
