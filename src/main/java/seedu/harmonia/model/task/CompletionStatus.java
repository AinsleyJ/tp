package seedu.harmonia.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.harmonia.commons.util.AppUtil.checkArgument;

/**
 * Represents a Task's completion status in the task list.
 * Guarantees: immutable;
 */
public class CompletionStatus {

    public static final String MESSAGE_CONSTRAINTS =
            "Completion status must be either true or false";
    public final String value;
    private final Boolean booleanValue;

    /**
     * Constructs an {@code CompletionStatus}.
     *
     * @param completionStatus A valid completion status.
     */
    public CompletionStatus(String completionStatus) {
        requireNonNull(completionStatus);
        checkArgument(isValidCompletionStatus(completionStatus), MESSAGE_CONSTRAINTS);
        value = completionStatus;
        booleanValue = Boolean.parseBoolean(completionStatus);
    }

    /**
     * Returns true if a given string is a valid CompletionStatus.
     */
    public static boolean isValidCompletionStatus(String test) {
        return ("true".equals(test) || "false".equals(test));
    }

    @Override
    public String toString() {
        return booleanValue ? "true" : "false";
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CompletionStatus // instanceof handles nulls
                && booleanValue.equals((((CompletionStatus) other).booleanValue))); // state check
    }
}
