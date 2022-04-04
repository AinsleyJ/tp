package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Task's description in the task list.
 * Guarantees: immutable;
 */
public class Description implements Comparable<Description> {

    public static final String MESSAGE_CONSTRAINTS =
            "Description should only contain alphanumeric characters and punctuations,"
                    + " and it should contain 1-255 characters.";

    /*
     * The first character of the description must not be a whitespace.
     * For other characters, they must be printable or whitespaces.
     * The maximum number of characters allowed is 255.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}\\p{Punct}][\\p{Alnum}\\p{Punct}\\s]{0,254}";
    public final String value;

    /**
     * Constructs a {@code Description}.
     *
     * @param description A valid description.
     */
    public Description(String description) {
        requireNonNull(description);
        checkArgument(isValidDescription(description), MESSAGE_CONSTRAINTS);
        value = description;
    }

    /**
     * Returns true if a given string is a valid description.
     */
    public static boolean isValidDescription(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Description // instanceof handles nulls
                && value.equals(((Description) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public int compareTo(Description d) {
        return this.value.compareTo(d.value);
    }
}
