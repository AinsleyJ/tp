package seedu.harmonia.model.task;

import java.util.Set;
import java.util.function.Predicate;

import seedu.harmonia.commons.util.StringUtil;

/**
 * Tests that a {@code Task}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Task> {
    private final Set<String> keywords;

    public NameContainsKeywordsPredicate(Set<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Task task) {
        if (keywords.size() == 0) {
            // If the list passed in is empty, returns true.
            return true;
        }
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnorePunctuationAndCase(task.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
