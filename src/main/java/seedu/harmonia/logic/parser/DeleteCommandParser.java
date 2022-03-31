package seedu.harmonia.logic.parser;

import static seedu.harmonia.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.List;

import seedu.harmonia.commons.core.index.Index;
import seedu.harmonia.logic.commands.DeleteCommand;
import seedu.harmonia.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        try {
            List<Index> indexes = MassOpsParser.massOpProcessor(args);
            return new DeleteCommand(indexes);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), pe);
        }
    }

}
