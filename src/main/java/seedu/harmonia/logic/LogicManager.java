package seedu.harmonia.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.harmonia.commons.core.GuiSettings;
import seedu.harmonia.commons.core.LogsCenter;
import seedu.harmonia.logic.commands.Command;
import seedu.harmonia.logic.commands.CommandResult;
import seedu.harmonia.logic.commands.exceptions.CommandException;
import seedu.harmonia.logic.parser.HarmoniaParser;
import seedu.harmonia.logic.parser.exceptions.ParseException;
import seedu.harmonia.model.Model;
import seedu.harmonia.model.ReadOnlyTaskList;
import seedu.harmonia.model.task.Task;
import seedu.harmonia.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final HarmoniaParser harmoniaParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        harmoniaParser = new HarmoniaParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = harmoniaParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveTaskList(model.getTaskList());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyTaskList getTaskList() {
        return model.getTaskList();
    }

    @Override
    public ObservableList<Task> getFilteredTaskList() {
        return model.getFilteredTaskList();
    }

    @Override
    public Path getTaskListFilePath() {
        return model.getTaskListFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}
