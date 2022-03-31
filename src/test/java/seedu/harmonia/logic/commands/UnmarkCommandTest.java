//package seedu.harmonia.logic.commands;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static seedu.harmonia.logic.commands.CommandTestUtil.VALID_COMPLETION_STATUS_FALSE;
//import static seedu.harmonia.logic.commands.CommandTestUtil.assertCommandFailure;
//import static seedu.harmonia.logic.commands.CommandTestUtil.assertCommandSuccess;
//import static seedu.harmonia.logic.commands.CommandTestUtil.showTaskAtIndex;
//import static seedu.harmonia.testutil.TypicalIndexes.INDEX_FIFTH_TASK;
//import static seedu.harmonia.testutil.TypicalIndexes.INDEX_FIRST_TASK;
//import static seedu.harmonia.testutil.TypicalIndexes.INDEX_SECOND_TASK;
//import static seedu.harmonia.testutil.TypicalTasks.getTypicalTaskList;
//
//import org.junit.jupiter.api.Test;
//
//import seedu.harmonia.commons.core.Messages;
//import seedu.harmonia.commons.core.index.Index;
//import seedu.harmonia.model.Model;
//import seedu.harmonia.model.ModelManager;
//import seedu.harmonia.model.UserPrefs;
//import seedu.harmonia.model.task.Task;
//import seedu.harmonia.testutil.TaskBuilder;
//
///**
// * Contains integration tests (interaction with the Model) and unit tests for
// * {@code UnmarkCommand}.
// */
//public class UnmarkCommandTest {
//
//    private Model model = new ModelManager(getTypicalTaskList(), new UserPrefs());
//
//    @Test
//    public void execute_validIndexUnfilteredList_success() {
//        Task taskToUnmark = model.getFilteredTaskList().get(INDEX_SECOND_TASK.getZeroBased());
//        Task unmarkedTask = new TaskBuilder(taskToUnmark).withCompletionStatus(VALID_COMPLETION_STATUS_FALSE).build();
//
//        UnmarkCommand unmarkCommand = new UnmarkCommand(INDEX_SECOND_TASK);
//        String expectedMessage = String.format(UnmarkCommand.MESSAGE_UNMARK_TASK_SUCCESS, unmarkedTask);
//
//        ModelManager expectedModel = new ModelManager(model.getTaskList(), new UserPrefs());
//        expectedModel.strictSetTask(model.getFilteredTaskList().get(INDEX_SECOND_TASK.getZeroBased()), unmarkedTask);
//
//        assertCommandSuccess(unmarkCommand, model, expectedMessage, expectedModel);
//    }
//
//    @Test
//    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
//        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
//        MarkCommand markCommand = new MarkCommand(outOfBoundIndex);
//
//        assertCommandFailure(markCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
//    }
//
//    @Test
//    public void execute_validCompletedTaskUnfilteredList_throwsCommandException() {
//        // INDEX FIRST TASK: default completion status set to false
//        Task taskToUnmark = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
//        assertEquals(taskToUnmark.getCompletionStatus().value, "false");
//
//        UnmarkCommand unmarkCommand = new UnmarkCommand(INDEX_FIRST_TASK);
//
//        assertCommandFailure(unmarkCommand, model, UnmarkCommand.MESSAGE_TASK_ALREADY_UNCOMPLETED);
//    }
//
//
//    @Test
//    public void execute_validIndexFilteredList_success() {
//        showTaskAtIndex(model, INDEX_FIFTH_TASK);
//
//        // Note: FilteredTaskList will have only taskToUnmark i.e. taskToUnmark is at the INDEX_FIRST_TASK
//        Task taskToUnmark = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
//        Task unmarkedTask = new TaskBuilder(taskToUnmark).withCompletionStatus(VALID_COMPLETION_STATUS_FALSE).build();
//
//        UnmarkCommand unmarkCommand = new UnmarkCommand(INDEX_FIRST_TASK);
//
//        String expectedMessage = String.format(UnmarkCommand.MESSAGE_UNMARK_TASK_SUCCESS, unmarkedTask);
//
//        ModelManager expectedModel = new ModelManager(model.getTaskList(), new UserPrefs());
//        expectedModel.strictSetTask(model.getFilteredTaskList().get(0), unmarkedTask);
//
//        assertCommandSuccess(unmarkCommand, model, expectedMessage, expectedModel);
//    }
//
//    @Test
//    public void execute_invalidIndexFilteredList_throwsCommandException() {
//        showTaskAtIndex(model, INDEX_FIRST_TASK);
//
//        Index outOfBoundIndex = INDEX_SECOND_TASK;
//        // ensures that outOfBoundIndex is still in bounds of task list
//        assertTrue(outOfBoundIndex.getZeroBased() < model.getTaskList().getTaskList().size());
//
//        UnmarkCommand unmarkCommand = new UnmarkCommand(outOfBoundIndex);
//
//        assertCommandFailure(unmarkCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
//    }
//
//
//    @Test
//    public void equals() {
//        UnmarkCommand unmarkFirstCommand = new UnmarkCommand(INDEX_FIRST_TASK);
//        UnmarkCommand unmarkSecondCommand = new UnmarkCommand(INDEX_SECOND_TASK);
//
//        // same object -> returns true
//        assertTrue(unmarkFirstCommand.equals(unmarkFirstCommand));
//
//        // same values -> returns true
//        UnmarkCommand unmarkFirstCommandCopy = new UnmarkCommand(INDEX_FIRST_TASK);
//        assertTrue(unmarkFirstCommand.equals(unmarkFirstCommandCopy));
//
//        // different types -> returns false
//        assertFalse(unmarkFirstCommand.equals(1));
//
//        // null -> returns false
//        assertFalse(unmarkFirstCommand.equals(null));
//
//        // different task -> returns false
//        assertFalse(unmarkFirstCommand.equals(unmarkSecondCommand));
//    }
//
//}
