package seedu.harmonia.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.harmonia.model.Model;
import seedu.harmonia.model.TaskList;

/**
 * Clears the task list.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Task list has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setTaskList(new TaskList());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
