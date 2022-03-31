package seedu.harmonia.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.harmonia.commons.exceptions.DataConversionException;
import seedu.harmonia.model.ReadOnlyTaskList;
import seedu.harmonia.model.ReadOnlyUserPrefs;
import seedu.harmonia.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends TaskListStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getTaskListFilePath();

    @Override
    Optional<ReadOnlyTaskList> readTaskList() throws DataConversionException, IOException;

    @Override
    void saveTaskList(ReadOnlyTaskList taskList) throws IOException;

}
