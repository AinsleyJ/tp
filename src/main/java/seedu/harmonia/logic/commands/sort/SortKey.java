package seedu.harmonia.logic.commands.sort;

import static java.util.Objects.requireNonNull;
import static seedu.harmonia.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Sort Keys that can be used for sorting tasks.
 */
public enum SortKey {
    NAME("name"),
    DEADLINE("deadline"),
    PRIORITY("priority");

    public static final String MESSAGE_CONSTRAINTS =
            "Sort key should be in the format name, deadline, or priority";

    private final String label;

    SortKey(String sortKey) {
        requireAllNonNull(sortKey);
        this.label = sortKey;
    }

    /**
     * Returns true if {@code String sortKey} is a valid {@code SortKey}, false otherwise.
     */
    public static boolean isValidSortKey(String sortKey) {
        requireNonNull(sortKey);
        for (SortKey s : SortKey.values()) {
            if (s.label.equals(sortKey)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns {@code SortKey} based on its label.
     *
     * @throws IllegalArgumentException If the {@code sortKey} is an invalid label.
     */
    public static SortKey valueOfLabel(String sortKey) {
        requireNonNull(sortKey);
        for (SortKey s : SortKey.values()) {
            if (s.label.equals(sortKey)) {
                return s;
            }
        }
        throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
    }
}
