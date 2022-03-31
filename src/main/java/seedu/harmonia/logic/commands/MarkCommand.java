package seedu.harmonia.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.harmonia.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import seedu.harmonia.commons.core.Messages;
import seedu.harmonia.commons.core.index.Index;
import seedu.harmonia.logic.commands.exceptions.CommandException;
import seedu.harmonia.logic.parser.MassOpsParser;
import seedu.harmonia.model.Model;
import seedu.harmonia.model.tag.Tag;
import seedu.harmonia.model.task.CompletionStatus;
import seedu.harmonia.model.task.Deadline;
import seedu.harmonia.model.task.Description;
import seedu.harmonia.model.task.Name;
import seedu.harmonia.model.task.Priority;
import seedu.harmonia.model.task.Task;

/**
 * Marks a task identified using it's displayed index from the task list as completed.
 */
public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the task identified by the index number used in the displayed task list as completed.\n"
            + "Parameters: INDEX[ES] (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1, " + COMMAND_WORD + " 1 2 3";

    public static final String MESSAGE_TASK_ALREADY_COMPLETED = "This task is already marked as complete.";

    private final List<Index> targetIndexes;

    /**
     * Constructor for marking multiple indexes.
     *
     * @param targetIndexes an {@code ArrayList<Index>} which stores each index to be marked
     */
    public MarkCommand(List<Index> targetIndexes) {
        assert(targetIndexes != null && targetIndexes.size() != 0);
        this.targetIndexes = MassOpsParser.sortInAsc(targetIndexes);
    }

    /**
     * Creates a marked iteration of the Task provided.
     *
     * @param task task to be copied.
     * @return marked task.
     */
    private static Task createMarkedTask(Task task) {
        Name name = task.getName();
        Description description = task.getDescription();
        CompletionStatus completionStatus = new CompletionStatus("true");
        Deadline deadline = task.getDeadline();
        Priority priority = task.getPriority();
        Set<Tag> tags = task.getTags();

        Task markedTask = new Task(name, description, completionStatus, deadline, priority, tags);
        return markedTask;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();
        List<Task> markedTasks = new ArrayList<>();
        List<Index> markedTasksIndexes = new ArrayList<>();
        List<Index> alreadyMarkedIndexes = new ArrayList<>();
        List<Index> outOfBoundsIndexes = new ArrayList<>();


        // to mark each index in the task list individually.
        for (int i = 0; i < targetIndexes.size(); i++) {
            Index index = targetIndexes.get(i);

            if (isValidIndex(index, lastShownList) == 1) {
                alreadyMarkedIndexes.add(index);
            } else if (isValidIndex(index, lastShownList) == -1) {
                outOfBoundsIndexes.add(index);
            } else {
                Task taskToMark = lastShownList.get(index.getZeroBased());
                Task markedTask = createMarkedTask(taskToMark);
                markedTasks.add(markedTask);
                markedTasksIndexes.add(index);

                model.strictSetTask(taskToMark, markedTask);
                model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
            }
        }
        return result(markedTasks, markedTasksIndexes, alreadyMarkedIndexes, outOfBoundsIndexes);
    }

    /**
     * Converts the list of successfully marked tasks into a string to be returned to the user
     *
     * @param markedTasks
     * @return a {@code String} listings all the successfully marked tasks.
     */
    private String markedTasksToString(List<Task> markedTasks, List<Index> markedTasksIndexes) {
        String str = "Successfully Marked Tasks: \n";
        for (int i = 0; i < markedTasks.size(); i++) {
            str += markedTasksIndexes.get(i).getOneBased() + ". " + markedTasks.get(i) + "\n";
        }
        return str;
    }

    /**
     * Converts a list of indexes to a string to be returned to the user.
     *
     * @param indexes
     * @return a {@code String} of the list of indexes that are passed into the function.
     */
    private String indexesToString(List<Index> indexes) {
        String str = "" + indexes.get(0).getOneBased();
        for (int i = 1; i < indexes.size(); i++) {
            int index = indexes.get(i).getOneBased();
            if (indexes.size() > 1 && i == indexes.size() - 1) {
                str += " and " + index;
            } else {
                str += ", " + index;
            }
        }
        return str;
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MarkCommand // instanceof handles nulls
                && targetIndexes.equals(((MarkCommand) other).targetIndexes)); // state check
    }

    /**
     * Checks if index is a valid index to mark.
     * If index > size of targetIndexes, return -1.
     * If index is already marked, return 1.
     * If index is a valid index, return 0.
     *
     * @return an int representing validity of the index
     */
    private int isValidIndex(Index index, List<Task> taskList) {
        if (index.getZeroBased() > taskList.size() - 1 || index.getZeroBased() < 0) { //index out of bounds
            return -1;
        } else if (taskList.get(index.getZeroBased()).getCompletionStatus().toString().equals("true")) {
            //already marked index
            return 1;
        } else { //valid index
            return 0;
        }
    }

    /**
     * Processes the lists containing the results of the marking of indexes and returns the result to the user.
     *
     * @param markedTasks
     * @param markedTasksIndexes
     * @param alreadyMarkedIndexes
     * @param outOfBoundsIndexes
     * @return CommandResult of the marking of the inputted indexes.
     * @throws CommandException
     */
    private CommandResult result(List<Task> markedTasks, List<Index> markedTasksIndexes,
                                 List<Index> alreadyMarkedIndexes,
                                 List<Index> outOfBoundsIndexes) throws CommandException {
        StringBuilder errorString = new StringBuilder();

        if (!alreadyMarkedIndexes.isEmpty()) {
            errorString.append("Index " + indexesToString(alreadyMarkedIndexes) + ": "
                    + MESSAGE_TASK_ALREADY_COMPLETED + "\n");
        }

        if (!outOfBoundsIndexes.isEmpty()) {
            errorString.append("Index " + indexesToString(outOfBoundsIndexes) + ": "
                    + Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX + "\n");
        }

        if (errorString.length() != 0) { //throw error if any
            if (!markedTasks.isEmpty()) {
                errorString.append(markedTasksToString(markedTasks, markedTasksIndexes));
            }
            throw new CommandException(errorString.toString());
        }

        return new CommandResult(markedTasksToString(markedTasks, markedTasksIndexes));

    }
}
