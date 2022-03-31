package seedu.harmonia.model.task;

import static seedu.harmonia.model.task.Deadline.MAX_DEADLINE;
import static seedu.harmonia.model.task.Deadline.MIN_DEADLINE;

import java.util.Optional;
import java.util.function.Predicate;

/**
 * Tests that a {@code Task}'s {@code Deadline} is within the range given.
 */
public class DeadlineInRangePredicate implements Predicate<Task> {

    private final Optional<Deadline> startDate;
    private final Optional<Deadline> endDate;

    /**
     * Constructor for DeadlineInRangePredicate.
     *
     * @param startDate The lower bound of the range of deadline (inclusive).
     *                  If the given value is null, it means that there is no lower bound.
     * @param endDate the upper bound of the range of deadline (inclusive)
     *                If the given value is null, it means that there is no upper bound.
     */
    public DeadlineInRangePredicate(Deadline startDate, Deadline endDate) {
        this.startDate = Optional.ofNullable(startDate);
        this.endDate = Optional.ofNullable(endDate);
    }

    @Override
    public boolean test(Task task) {
        Deadline deadline = task.getDeadline();
        return deadline.compareTo(startDate.orElse(MIN_DEADLINE)) >= 0
                && deadline.compareTo(endDate.orElse(MAX_DEADLINE)) <= 0;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeadlineInRangePredicate // instanceof handles nulls
                && startDate.equals(((DeadlineInRangePredicate) other).startDate)
                && endDate.equals(((DeadlineInRangePredicate) other).endDate)); // state check
    }
}
