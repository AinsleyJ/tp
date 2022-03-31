package seedu.harmonia.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.harmonia.commons.core.GuiSettings;
import seedu.harmonia.logic.commands.CommandResult;
import seedu.harmonia.logic.commands.exceptions.CommandException;
import seedu.harmonia.logic.parser.exceptions.ParseException;
import seedu.harmonia.model.ReadOnlyTaskList;
import seedu.harmonia.model.task.Task;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the TaskList.
     *
     * @see seedu.harmonia.model.Model#getTaskList()
     */
    ReadOnlyTaskList getTaskList();

    /** Returns an unmodifiable view of the filtered list of tasks */
    ObservableList<Task> getFilteredTaskList();

    /**
     * Returns the user prefs' task list file path.
     */
    Path getTaskListFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
