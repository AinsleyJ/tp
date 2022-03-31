package seedu.harmonia.model;

import java.nio.file.Path;

import seedu.harmonia.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getTaskListFilePath();

}
