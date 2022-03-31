package seedu.harmonia.model;

import static java.util.Objects.requireNonNull;
import static seedu.harmonia.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.harmonia.logic.commands.sort.ComparatorFactory.DEFAULT_COMPARATOR;

import java.nio.file.Path;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.harmonia.commons.core.GuiSettings;
import seedu.harmonia.commons.core.LogsCenter;
import seedu.harmonia.model.tag.Tag;
import seedu.harmonia.model.task.Task;

/**
 * Represents the in-memory model of the task list data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final TaskList taskList;
    private final UserPrefs userPrefs;
    private final FilteredList<Task> filteredTasks;
    private final SortedList<Task> sortedTasks;
    private final TagList tagList;


    /**
     * Initializes a ModelManager with the given taskList and userPrefs.
     */
    public ModelManager(ReadOnlyTaskList taskList, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(taskList, userPrefs);

        logger.fine("Initializing with task list: " + taskList + " and user prefs " + userPrefs);

        this.taskList = new TaskList(taskList);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredTasks = new FilteredList<>(this.taskList.getTaskList());
        sortedTasks = new SortedList<>(filteredTasks, DEFAULT_COMPARATOR);
        tagList = new TagList(this.taskList);
    }

    public ModelManager() {
        this(new TaskList(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getTaskListFilePath() {
        return userPrefs.getTaskListFilePath();
    }

    @Override
    public void setTaskListFilePath(Path taskListFilePath) {
        requireNonNull(taskListFilePath);
        userPrefs.setTaskListFilePath(taskListFilePath);
    }

    //=========== TaskList ================================================================================

    @Override
    public void setTaskList(ReadOnlyTaskList taskList) {
        this.taskList.resetData(taskList);
    }

    @Override
    public ReadOnlyTaskList getTaskList() {
        return taskList;
    }

    @Override
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return taskList.hasTask(task);
    }

    @Override
    public void deleteTask(Task target) {
        taskList.removeTask(target);
        tagList.removeTagsOfTask(target);
    }

    @Override
    public void addTask(Task task) {
        taskList.addTask(task);
        tagList.addTagsOfTask(task);
        updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
    }

    @Override
    public void setTask(Task target, Task editedTask) {
        requireAllNonNull(target, editedTask);

        taskList.setTask(target, editedTask);
        tagList.setTag(target, editedTask);
    }

    /**
     * Replaces the task {@code target} in the list with {@code editedTask} under stricter conditions.
     * {@code target} must exist in the list.
     * The task identity of {@code editedTask} must not be the same as another existing task in the list.
     */
    @Override
    public void strictSetTask(Task target, Task markedTask) {
        requireAllNonNull(target, markedTask);
        taskList.strictSetTask(target, markedTask);
    }

    //=========== Filtered Task List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Task} backed by the internal list of
     * {@code versionedTaskList}
     */
    @Override
    public ObservableList<Task> getFilteredTaskList() {
        return filteredTasks;
    }

    @Override
    public void updateFilteredTaskList(Predicate<Task> predicate) {
        requireNonNull(predicate);
        filteredTasks.setPredicate(predicate);
    }

    //=========== Sorted Task List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Task} backed by the internal list of
     * {@code versionedTaskList}
     */
    @Override
    public ObservableList<Task> getSortedTaskList() {
        return sortedTasks;
    }

    @Override
    public void updateSortedTaskList(Comparator<Task> comparator) {
        requireNonNull(comparator);
        sortedTasks.setComparator(comparator);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return taskList.equals(other.taskList)
                && userPrefs.equals(other.userPrefs)
                && filteredTasks.equals(other.filteredTasks)
                && sortedTasks.equals(other.sortedTasks);
    }

    //=========== Tag List ==================================================================================

    @Override
    public Set<Tag> getTagList() {
        return Collections.unmodifiableSet(tagList.getTagList());
    }

}
