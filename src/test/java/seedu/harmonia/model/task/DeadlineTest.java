package seedu.harmonia.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.harmonia.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Deadline(null));
    }

    @Test
    public void constructor_invalidDeadline_throwsIllegalArgumentException() {
        String invalidDeadline = "";
        assertThrows(IllegalArgumentException.class, () -> new Deadline(invalidDeadline));
    }

    @Test
    public void isValidDeadline() {
        // null date
        assertThrows(NullPointerException.class, () -> Deadline.isValidDeadline(null));

        // blank date
        assertFalse(Deadline.isValidDeadline("")); // empty string
        assertFalse(Deadline.isValidDeadline(" ")); // spaces only

        // missing parts
        assertFalse(Deadline.isValidDeadline("10-22")); // missing year
        assertFalse(Deadline.isValidDeadline("2022-10")); // missing month
        assertFalse(Deadline.isValidDeadline("2022-22")); // missing day

        // invalid parts
        assertFalse(Deadline.isValidDeadline("2022-10-32")); // day exceeds limit
        assertFalse(Deadline.isValidDeadline("2022-13-26")); // month exceeds limit
        assertFalse(Deadline.isValidDeadline("999-13-26")); // year under limit
        assertFalse(Deadline.isValidDeadline("10000-13-26")); // year over limit

        // invalid format
        assertFalse(Deadline.isValidDeadline("26-10-2022")); // DD-MM-YYYY
        assertFalse(Deadline.isValidDeadline("10-26-2022")); // MM-DD-YYYY
        assertFalse(Deadline.isValidDeadline("2022/10/22")); // DD/MM/YYYY
        assertFalse(Deadline.isValidDeadline("--03-20")); // ISO_8601 format without year
        assertFalse(Deadline.isValidDeadline("20090106")); // Basic ISO_8601 format
        assertFalse(Deadline.isValidDeadline("2nd October 2007"));
        assertFalse(Deadline.isValidDeadline("2 October 2007"));

        // valid date
        assertTrue(Deadline.isValidDeadline("2022-10-22")); // YYYY-MM-DD
    }
}
