package seedu.harmonia.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.harmonia.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PriorityTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Priority.valueOfLabel(null));
    }

    @Test
    public void constructor_invalidPriority_throwsIllegalArgumentException() {
        String invalidPriority = "";
        assertThrows(IllegalArgumentException.class, () -> Priority.valueOfLabel(invalidPriority));
    }

    @Test
    public void isValidPriority() {
        // null priority
        assertThrows(NullPointerException.class, () -> Priority.isValidPriority(null));

        // invalid priority
        assertFalse(Priority.isValidPriority("")); // empty string
        assertFalse(Priority.isValidPriority(" ")); // spaces only
        assertFalse(Priority.isValidPriority("highest")); // invalid enum
        assertFalse(Priority.isValidPriority("ㅁㅂㅇㅈㅊㅋㅌㅍㅎ")); // non-english characters
        assertFalse(Priority.isValidPriority("╲ʕ·ᴥ· ╲ʔ")); // symbols
        assertFalse(Priority.isValidPriority("☝")); // emojis

        // valid priority
        assertTrue(Priority.isValidPriority("low"));
        assertTrue(Priority.isValidPriority("medium"));
        assertTrue(Priority.isValidPriority("high"));
    }
}
