package seedu.harmonia.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.harmonia.model.ReadOnlyTaskList;
import seedu.harmonia.model.TaskList;
import seedu.harmonia.model.tag.Tag;
import seedu.harmonia.model.task.CompletionStatus;
import seedu.harmonia.model.task.Deadline;
import seedu.harmonia.model.task.Description;
import seedu.harmonia.model.task.Name;
import seedu.harmonia.model.task.Priority;
import seedu.harmonia.model.task.Task;

/**
 * Contains utility methods for populating {@code TaskList} with sample data.
 */
public class SampleDataUtil {
    public static Task[] getSampleTasks() {
        return new Task[] {
            new Task(new Name("Complete Tutorial"), new Description("Hand draw UML diagrams"),
                new CompletionStatus("true"), new Deadline("2022-03-10"), Priority.LOW, getTagSet("CS2103T")),
            new Task(new Name("Prepare for Presentation"), new Description("Finalise slides and memorise script"),
                new CompletionStatus("false"), new Deadline("2022-03-12"), Priority.LOW, getTagSet("CS2101")),
            new Task(new Name("Review individual projects"),
                new Description("Review two ips that are sent to my email, for 4 participation points"),
                new CompletionStatus("true"), new Deadline("2022-03-13"), Priority.LOW,
                getTagSet("CS2103T", "participation")),
            new Task(new Name("Revise for Midterm Test"), new Description("Chapters 1-5"),
                new CompletionStatus("false"), new Deadline("2022-03-25"), Priority.LOW, getTagSet("CS2102")),
            new Task(new Name("Complete Assignment"),
                new Description("Name submission file as assignment1_name and upload to Luminus folder"),
                new CompletionStatus("false"), new Deadline("2022-04-01"), Priority.LOW, getTagSet("CS2105"))
        };
    }

    public static ReadOnlyTaskList getSampleTaskList() {
        TaskList sampleTl = new TaskList();
        for (Task sampleTask : getSampleTasks()) {
            sampleTl.addTask(sampleTask);
        }
        return sampleTl;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
