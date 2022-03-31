package seedu.harmonia.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.harmonia.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DescriptionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Description(null));
    }

    @Test
    public void constructor_invalidDescription_throwsIllegalArgumentException() {
        String invalidDescription = "";
        assertThrows(IllegalArgumentException.class, () -> new Description(invalidDescription));
    }

    @Test
    public void isValidDescription() {
        // null description
        assertThrows(NullPointerException.class, () -> Description.isValidDescription(null));

        // invalid descriptions
        assertFalse(Description.isValidDescription("")); // empty string
        assertFalse(Description.isValidDescription(" ")); // spaces only
        assertFalse(Description.isValidDescription("ㅁㅂㅇㅈㅊㅋㅌㅍㅎ")); // non-english characters
        assertFalse(Description.isValidDescription("╲ʕ·ᴥ· ╲ʔ")); // symbols
        assertFalse(Description.isValidDescription("☝")); // emojis
        assertFalse(Description.isValidDescription(" Complete CS2103T Week 8 Readings")); // first char whitespace
        assertFalse(Description.isValidDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit, "
                + "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis "
                + "nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure "
                + "dolor em")); // 256 characters

        // valid description
        assertTrue(Description.isValidDescription("Complete CS2103T Week 8 Readings"));
        assertTrue(Description.isValidDescription("H")); // one character
        assertTrue(Description.isValidDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit, "
                + "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis "
                + "nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure "
                + "dolor e")); // 255 characters
    }
}
