package seedu.harmonia.logic.parser;

import static seedu.harmonia.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.harmonia.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.harmonia.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.harmonia.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.harmonia.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.harmonia.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.harmonia.logic.commands.AddCommand;
import seedu.harmonia.logic.parser.exceptions.ParseException;
import seedu.harmonia.model.tag.Tag;
import seedu.harmonia.model.task.CompletionStatus;
import seedu.harmonia.model.task.Deadline;
import seedu.harmonia.model.task.Description;
import seedu.harmonia.model.task.Name;
import seedu.harmonia.model.task.Priority;
import seedu.harmonia.model.task.Task;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DEADLINE, PREFIX_DESCRIPTION,
                        PREFIX_PRIORITY, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_DEADLINE, PREFIX_DESCRIPTION, PREFIX_PRIORITY)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Description description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
        Deadline deadline = ParserUtil.parseDeadline(argMultimap.getValue(PREFIX_DEADLINE).get());
        Priority priority = ParserUtil.parsePriority(argMultimap.getValue(PREFIX_PRIORITY).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        CompletionStatus completionStatus = new CompletionStatus("false");
        Task task = new Task(name, description, completionStatus, deadline, priority, tagList);

        return new AddCommand(task);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
