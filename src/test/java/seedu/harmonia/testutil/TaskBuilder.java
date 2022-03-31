package seedu.harmonia.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.harmonia.model.tag.Tag;
import seedu.harmonia.model.task.CompletionStatus;
import seedu.harmonia.model.task.Deadline;
import seedu.harmonia.model.task.Description;
import seedu.harmonia.model.task.Name;
import seedu.harmonia.model.task.Priority;
import seedu.harmonia.model.task.Task;
import seedu.harmonia.model.util.SampleDataUtil;

/**
 * A utility class to help with building Task objects.
 */
public class TaskBuilder {

    public static final String DEFAULT_NAME = "CS2103T Tutorial";
    public static final String DEFAULT_DESCRIPTION = "Tutorial for Week 9";
    public static final String DEFAULT_DEADLINE = "2022-03-16";
    public static final String DEFAULT_COMPLETION_STATUS = "false";
    public static final String DEFAULT_PRIORITY = "low";

    private Name name;
    private Description description;
    private Deadline deadline;
    private CompletionStatus completionStatus;
    private Priority priority;
    private Set<Tag> tags;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        name = new Name(DEFAULT_NAME);
        description = new Description(DEFAULT_DESCRIPTION);
        deadline = new Deadline(DEFAULT_DEADLINE);
        completionStatus = new CompletionStatus(DEFAULT_COMPLETION_STATUS);
        priority = Priority.valueOfLabel(DEFAULT_PRIORITY);
        tags = new HashSet<>();
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        name = taskToCopy.getName();
        description = taskToCopy.getDescription();
        deadline = taskToCopy.getDeadline();
        completionStatus = taskToCopy.getCompletionStatus();
        priority = taskToCopy.getPriority();
        tags = new HashSet<>(taskToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Task} that we are building.
     */
    public TaskBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Task} that we are building.
     */
    public TaskBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code CompletionStatus} of the {@code Task} that we are building.
     */
    public TaskBuilder withCompletionStatus(String completionStatus) {
        this.completionStatus = new CompletionStatus(completionStatus);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Task} that we are building.
     */
    public TaskBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code Task} that we are building.
     */
    public TaskBuilder withDeadline(String deadline) {
        this.deadline = new Deadline(deadline);
        return this;
    }

    /**
     * Sets the {@code Priority} of the {@code Task} that we are building.
     */
    public TaskBuilder withPriority(String priority) {
        this.priority = Priority.valueOfLabel(priority);
        return this;
    }

    public Task build() {
        return new Task(name, description, completionStatus, deadline, priority, tags);
    }

}
