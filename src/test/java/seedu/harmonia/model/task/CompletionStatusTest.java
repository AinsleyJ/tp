package seedu.harmonia.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.harmonia.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CompletionStatusTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CompletionStatus(null));
    }

    @Test
    public void constructor_invalidCompletionStatus_throwsIllegalArgumentException() {
        String invalidCompletionStatus = "";
        assertThrows(IllegalArgumentException.class, () -> new CompletionStatus(invalidCompletionStatus));
    }

    @Test
    public void isValidCompletionStatus() {
        // invalid completion status
        assertFalse(CompletionStatus.isValidCompletionStatus(null));
        assertFalse(CompletionStatus.isValidCompletionStatus(""));
        assertFalse(CompletionStatus.isValidCompletionStatus("F"));
        assertFalse(CompletionStatus.isValidCompletionStatus("T"));

        // valid completion status
        assertTrue(CompletionStatus.isValidCompletionStatus("true"));
        assertTrue(CompletionStatus.isValidCompletionStatus("false"));
    }
}
