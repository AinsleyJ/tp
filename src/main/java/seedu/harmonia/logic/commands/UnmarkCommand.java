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
 * Marks a task identified using it's displayed index from the task list as uncompleted.
 */
public class UnmarkCommand extends Command {

    public static final String COMMAND_WORD = "unmark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the task identified by the index number used in the displayed task list as uncompleted.\n"
            + "Parameters: INDEX[ES] (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1, " + COMMAND_WORD + " 1 2 3";

    public static final String MESSAGE_TASK_ALREADY_UNCOMPLETED = "This task is already marked as incomplete.";

    private final List<Index> targetIndexes;

    /**
     * Constructor for unmarking multiple indexes.
     *
     * @param targetIndexes an {@code ArrayList<Index>} which stores each index to be unmarked
     */
    public UnmarkCommand(List<Index> targetIndexes) {
        assert(targetIndexes != null && targetIndexes.size() != 0);
        this.targetIndexes = MassOpsParser.sortInAsc(targetIndexes);
    }

    /**
     * Creates an unmarked iteration of the Task provided.
     *
     * @param task task to be copied.
     * @return unmarked task.
     */
    private static Task createUnmarkedTask(Task task) {
        Name name = task.getName();
        Description description = task.getDescription();
        CompletionStatus completionStatus = new CompletionStatus("false");
        Deadline deadline = task.getDeadline();
        Priority priority = task.getPriority();
        Set<Tag> tags = task.getTags();

        Task unmarkedTask = new Task(name, description, completionStatus, deadline, priority, tags);
        return unmarkedTask;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getSortedTaskList();
        List<Task> unmarkedTasks = new ArrayList<>();
        List<Index> unmarkedTasksIndexes = new ArrayList<>();
        List<Index> alreadyUnmarkedIndexes = new ArrayList<>();
        List<Index> outOfBoundsIndexes = new ArrayList<>();


        // to unmark each index in the task list individually.
        for (int i = 0; i < targetIndexes.size(); i++) {
            Index index = targetIndexes.get(i);

            if (isValidIndex(index, lastShownList) == 1) {
                alreadyUnmarkedIndexes.add(index);
            } else if (isValidIndex(index, lastShownList) == -1) {
                outOfBoundsIndexes.add(index);
            } else {
                Task taskToUnmark = lastShownList.get(index.getZeroBased());
                Task unmarkedTask = createUnmarkedTask(taskToUnmark);
                unmarkedTasks.add(unmarkedTask);
                unmarkedTasksIndexes.add(index);

                model.strictSetTask(taskToUnmark, unmarkedTask);
                model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
            }
        }
        return result(unmarkedTasks, unmarkedTasksIndexes, alreadyUnmarkedIndexes, outOfBoundsIndexes);

    }

    /**
     * Converts the list of successfully unmarked tasks into a string to be returned to the user.
     *
     * @param unmarkedTasks
     * @return a {@code String} listings all the successfully unmarked tasks.
     */
    private String unmarkedTasksToString(List<Task> unmarkedTasks, List<Index> unmarkedTasksIndexes) {
        String str = "Successfully Unmarked Tasks: \n";
        for (int i = 0; i < unmarkedTasks.size(); i++) {
            str += unmarkedTasksIndexes.get(i).getOneBased() + ". " + unmarkedTasks.get(i) + "\n";
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
                || (other instanceof UnmarkCommand // instanceof handles nulls
                && targetIndexes.equals(((UnmarkCommand) other).targetIndexes)); // state check
    }

    /**
     * Checks if index is a valid index to unmark.
     * If index > size of targetIndexes, return -1.
     * If index is already unmarked, return 1.
     * If index is a valid index, return 0.
     *
     * @return an int representing validity of the index
     */
    private int isValidIndex(Index index, List<Task> taskList) {
        if (index.getZeroBased() > taskList.size() - 1 || index.getZeroBased() < 0) { //index out of bounds
            return -1;
        } else if (taskList.get(index.getZeroBased()).getCompletionStatus().toString().equals("false")) {
            //already unmarked index
            return 1;
        } else { //valid index
            return 0;
        }
    }

    /**
     * Processes the lists containing the results of the unmarking of indexes and returns the result to the user.
     *
     * @param unmarkedTasks
     * @param alreadyUnmarkedIndexes
     * @param outOfBoundsIndexes
     * @return CommandResult of unmarking the inputted indexes.
     * @throws CommandException
     */
    private CommandResult result(List<Task> unmarkedTasks, List<Index> unmarkedTasksIndexes,
                                 List<Index> alreadyUnmarkedIndexes,
                                 List<Index> outOfBoundsIndexes) throws CommandException {
        StringBuilder errorString = new StringBuilder();

        if (!alreadyUnmarkedIndexes.isEmpty()) {
            errorString.append("Index " + indexesToString(alreadyUnmarkedIndexes) + ": "
                    + MESSAGE_TASK_ALREADY_UNCOMPLETED + "\n");
        }

        if (!outOfBoundsIndexes.isEmpty()) {
            errorString.append("Index " + indexesToString(outOfBoundsIndexes) + ": "
                    + Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX + "\n");
        }

        if (errorString.length() != 0) { //throw error if any
            if (!unmarkedTasks.isEmpty()) {
                errorString.append(unmarkedTasksToString(unmarkedTasks, unmarkedTasksIndexes));
            }
            throw new CommandException(errorString.toString());
        }

        return new CommandResult(unmarkedTasksToString(unmarkedTasks, unmarkedTasksIndexes));
    }
}
