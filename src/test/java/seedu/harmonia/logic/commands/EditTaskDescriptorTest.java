//package seedu.harmonia.logic.commands;
//
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static seedu.harmonia.logic.commands.CommandTestUtil.DESC_TUTORIAL;
//import static seedu.harmonia.logic.commands.CommandTestUtil.DESC_MIDTERM;
//import static seedu.harmonia.logic.commands.CommandTestUtil.VALID_DEADLINE_MIDTERM;
//import static seedu.harmonia.logic.commands.CommandTestUtil.VALID_DESCRIPTION_MIDTERM;
//import static seedu.harmonia.logic.commands.CommandTestUtil.VALID_NAME_MIDTERM;
//import static seedu.harmonia.logic.commands.CommandTestUtil.VALID_TAG_TEST;
//
//import org.junit.jupiter.api.Test;
//
//import seedu.harmonia.logic.commands.EditCommand.EditTaskDescriptor;
//import seedu.harmonia.testutil.EditTaskDescriptorBuilder;
//
//public class EditTaskDescriptorTest {
//
//    @Test
//    public void equals() {
//        // same values -> returns true
//        EditTaskDescriptor descriptorWithSameValues = new EditTaskDescriptor(DESC_TUTORIAL);
//        assertTrue(DESC_TUTORIAL.equals(descriptorWithSameValues));
//
//        // same object -> returns true
//        assertTrue(DESC_TUTORIAL.equals(DESC_TUTORIAL));
//
//        // null -> returns false
//        assertFalse(DESC_TUTORIAL.equals(null));
//
//        // different types -> returns false
//        assertFalse(DESC_TUTORIAL.equals(5));
//
//        // different values -> returns false
//        assertFalse(DESC_TUTORIAL.equals(DESC_MIDTERM));
//
//        // different name -> returns false
//        EditTaskDescriptor editedTutorial = new EditTaskDescriptorBuilder(DESC_TUTORIAL)
//                .withName(VALID_NAME_MIDTERM).build();
//        assertFalse(DESC_TUTORIAL.equals(editedTutorial));
//
//        // different description -> returns false
//        editedTutorial = new EditTaskDescriptorBuilder(DESC_TUTORIAL)
//                .withDescription(VALID_DESCRIPTION_MIDTERM).build();
//        assertFalse(DESC_TUTORIAL.equals(editedTutorial));
//
//        // different deadline -> returns false
//        editedTutorial = new EditTaskDescriptorBuilder(DESC_TUTORIAL).withDeadline(VALID_DEADLINE_MIDTERM).build();
//        assertFalse(DESC_TUTORIAL.equals(editedTutorial));
//
//        // different tags -> returns false
//        editedTutorial = new EditTaskDescriptorBuilder(DESC_TUTORIAL).withTags(VALID_TAG_TEST).build();
//        assertFalse(DESC_TUTORIAL.equals(editedTutorial));
//    }
//}
