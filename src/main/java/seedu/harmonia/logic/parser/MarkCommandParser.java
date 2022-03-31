package seedu.harmonia.logic.parser;

import static seedu.harmonia.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.List;

import seedu.harmonia.commons.core.index.Index;
import seedu.harmonia.logic.commands.MarkCommand;
import seedu.harmonia.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new MarkCommand object
 */
public class MarkCommandParser implements Parser<MarkCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the MarkCommand
     * and returns a MarkCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MarkCommand parse(String args) throws ParseException {
        try {
            List<Index> indexes = MassOpsParser.massOpProcessor(args);
            return new MarkCommand(indexes);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE), pe);
        }
    }
}
