package seedu.harmonia.logic.parser;

import static seedu.harmonia.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.harmonia.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.harmonia.logic.commands.ListCommand;
import seedu.harmonia.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ListCommand object
 */
public class ListCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the ListCommand
     * and returns a ListCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListCommand parse(String args) throws ParseException {

        String trimmedArgs = args.trim();

        if (trimmedArgs.equals("")) { // list all tasks command
            return new ListCommand(false);

        } else if (trimmedArgs.equals(PREFIX_TAG.getPrefix())) { // list all tags command
            return new ListCommand(true);

        } else { // command contains extraneous words
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        }
    }
}
