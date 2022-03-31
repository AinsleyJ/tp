package seedu.harmonia.logic.commands.sort;

import static java.util.Objects.requireNonNull;
import static seedu.harmonia.logic.parser.CliSyntax.PREFIX_SORT_KEY;
import static seedu.harmonia.logic.parser.CliSyntax.PREFIX_SORT_ORDER;

import java.util.Comparator;

import seedu.harmonia.commons.core.Messages;
import seedu.harmonia.logic.commands.Command;
import seedu.harmonia.logic.commands.CommandResult;
import seedu.harmonia.model.Model;
import seedu.harmonia.model.task.Task;


/**
 * Sorts and lists all tasks in task list by the sorting specified by user.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts all tasks by the specified sort key and order "
            + "and displays them as a list with index numbers. "
            + "Parameters: "
            + PREFIX_SORT_KEY + "SORT_BY "
            + PREFIX_SORT_ORDER + "SORT_ORDER\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_SORT_KEY + "deadline "
            + PREFIX_SORT_ORDER + "desc";


    /** {@code Comparator<Task>} used to sort tasks. */
    private final Comparator<Task> comparator;

    /**
     * Creates a {@code SortCommand} object.
     */
    public SortCommand(Comparator<Task> comparator) {
        requireNonNull(comparator);
        this.comparator = comparator;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateSortedTaskList(comparator);
        return new CommandResult(
                String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, model.getSortedTaskList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortCommand // instanceof handles nulls
                && comparator.equals(((SortCommand) other).comparator)); // state check
    }
}
