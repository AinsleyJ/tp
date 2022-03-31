package seedu.harmonia.logic.parser;

import static seedu.harmonia.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.List;

import seedu.harmonia.commons.core.index.Index;
import seedu.harmonia.logic.commands.UnmarkCommand;
import seedu.harmonia.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new UnmarkCommand object
 */
public class UnmarkCommandParser implements Parser<UnmarkCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the UnmarkCommand
     * and returns a UnmarkCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UnmarkCommand parse(String args) throws ParseException {
        try {
            List<Index> indexes = MassOpsParser.massOpProcessor(args);
            return new UnmarkCommand(indexes);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnmarkCommand.MESSAGE_USAGE), pe);
        }
    }

}
