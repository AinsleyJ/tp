//package seedu.harmonia.logic.commands;
//
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static seedu.harmonia.logic.commands.CommandTestUtil.DESC_MIDTERM;
//import static seedu.harmonia.logic.commands.CommandTestUtil.DESC_TUTORIAL;
//import static seedu.harmonia.logic.commands.CommandTestUtil.VALID_DESCRIPTION_MIDTERM;
//import static seedu.harmonia.logic.commands.CommandTestUtil.VALID_NAME_MIDTERM;
//import static seedu.harmonia.logic.commands.CommandTestUtil.VALID_TAG_TEST;
//import static seedu.harmonia.logic.commands.CommandTestUtil.assertCommandFailure;
//import static seedu.harmonia.logic.commands.CommandTestUtil.assertCommandSuccess;
//import static seedu.harmonia.logic.commands.CommandTestUtil.showTaskAtIndex;
//import static seedu.harmonia.testutil.TypicalIndexes.INDEX_FIRST_TASK;
//import static seedu.harmonia.testutil.TypicalIndexes.INDEX_SECOND_TASK;
//import static seedu.harmonia.testutil.TypicalTasks.getTypicalTaskList;
//
//import org.junit.jupiter.api.Test;
//
//import seedu.harmonia.commons.core.Messages;
//import seedu.harmonia.commons.core.index.Index;
//import seedu.harmonia.logic.commands.EditCommand.EditTaskDescriptor;
//import seedu.harmonia.model.Model;
//import seedu.harmonia.model.ModelManager;
//import seedu.harmonia.model.TaskList;
//import seedu.harmonia.model.UserPrefs;
//import seedu.harmonia.model.task.Task;
//import seedu.harmonia.testutil.EditTaskDescriptorBuilder;
//import seedu.harmonia.testutil.TaskBuilder;
//
///**
// * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
// */
//public class EditCommandTest {
//
//    private Model model = new ModelManager(getTypicalTaskList(), new UserPrefs());
//
//    @Test
//    public void execute_allFieldsSpecifiedUnfilteredList_success() {
//        Task editedTask = new TaskBuilder().build();
//        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder(editedTask).build();
//        EditCommand editCommand = new EditCommand(INDEX_FIRST_TASK, descriptor);
//
//        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTask);
//
//        Model expectedModel = new ModelManager(new TaskList(model.getTaskList()), new UserPrefs());
//        expectedModel.setTask(model.getFilteredTaskList().get(0), editedTask);
//
//        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
//    }
//
//    @Test
//    public void execute_someFieldsSpecifiedUnfilteredList_success() {
//        Index indexLastTask = Index.fromOneBased(model.getFilteredTaskList().size());
//        Task lastTask = model.getFilteredTaskList().get(indexLastTask.getZeroBased());
//
//        TaskBuilder taskInList = new TaskBuilder(lastTask);
//        Task editedTask = taskInList.withName(VALID_NAME_MIDTERM).withDescription(VALID_DESCRIPTION_MIDTERM)
//                .withTags(VALID_TAG_TEST).build();
//
//        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withName(VALID_NAME_MIDTERM)
//                .withDescription(VALID_DESCRIPTION_MIDTERM).withTags(VALID_TAG_TEST).build();
//        EditCommand editCommand = new EditCommand(indexLastTask, descriptor);
//
//        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTask);
//
//        Model expectedModel = new ModelManager(new TaskList(model.getTaskList()), new UserPrefs());
//        expectedModel.setTask(lastTask, editedTask);
//
//        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
//    }
//
//    @Test
//    public void execute_noFieldSpecifiedUnfilteredList_success() {
//        EditCommand editCommand = new EditCommand(INDEX_FIRST_TASK, new EditTaskDescriptor());
//        Task editedTask = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
//
//        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTask);
//
//        Model expectedModel = new ModelManager(new TaskList(model.getTaskList()), new UserPrefs());
//
//        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
//    }
//
//    @Test
//    public void execute_filteredList_success() {
//        showTaskAtIndex(model, INDEX_FIRST_TASK);
//
//        Task taskInFilteredList = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
//        Task editedTask = new TaskBuilder(taskInFilteredList).withName(VALID_NAME_MIDTERM).build();
//        EditCommand editCommand = new EditCommand(INDEX_FIRST_TASK,
//                new EditTaskDescriptorBuilder().withName(VALID_NAME_MIDTERM).build());
//
//        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTask);
//
//        Model expectedModel = new ModelManager(new TaskList(model.getTaskList()), new UserPrefs());
//        expectedModel.setTask(model.getFilteredTaskList().get(0), editedTask);
//
//        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
//    }
//
//    @Test
//    public void execute_duplicateTaskUnfilteredList_failure() {
//        Task firstTask = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
//        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder(firstTask).build();
//        EditCommand editCommand = new EditCommand(INDEX_SECOND_TASK, descriptor);
//
//        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_TASK);
//    }
//
//    @Test
//    public void execute_duplicateTaskFilteredList_failure() {
//        showTaskAtIndex(model, INDEX_FIRST_TASK);
//
//        // edit task in filtered list into a duplicate in task list
//        Task taskInList = model.getTaskList().getTaskList().get(INDEX_SECOND_TASK.getZeroBased());
//        EditCommand editCommand = new EditCommand(INDEX_FIRST_TASK,
//                new EditTaskDescriptorBuilder(taskInList).build());
//
//        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_TASK);
//    }
//
//    @Test
//    public void execute_invalidTaskIndexUnfilteredList_failure() {
//        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
//        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withName(VALID_NAME_MIDTERM).build();
//        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);
//
//        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
//    }
//
//    /**
//     * Edit filtered list where index is larger than size of filtered list,
//     * but smaller than size of task list
//     */
//    @Test
//    public void execute_invalidTaskIndexFilteredList_failure() {
//        showTaskAtIndex(model, INDEX_FIRST_TASK);
//        Index outOfBoundIndex = INDEX_SECOND_TASK;
//        // ensures that outOfBoundIndex is still in bounds of task list
//        assertTrue(outOfBoundIndex.getZeroBased() < model.getTaskList().getTaskList().size());
//
//        EditCommand editCommand = new EditCommand(outOfBoundIndex,
//                new EditTaskDescriptorBuilder().withName(VALID_NAME_MIDTERM).build());
//
//        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
//    }
//
//    @Test
//    public void equals() {
//        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_TASK, DESC_TUTORIAL);
//
//        // same values -> returns true
//        EditTaskDescriptor copyDescriptor = new EditTaskDescriptor(DESC_TUTORIAL);
//        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_TASK, copyDescriptor);
//        assertTrue(standardCommand.equals(commandWithSameValues));
//
//        // same object -> returns true
//        assertTrue(standardCommand.equals(standardCommand));
//
//        // null -> returns false
//        assertFalse(standardCommand.equals(null));
//
//        // different types -> returns false
//        assertFalse(standardCommand.equals(new ClearCommand()));
//
//        // different index -> returns false
//        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_TASK, DESC_TUTORIAL)));
//
//        // different descriptor -> returns false
//        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_TASK, DESC_MIDTERM)));
//    }
//
//}
