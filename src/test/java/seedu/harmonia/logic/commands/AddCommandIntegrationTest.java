//package seedu.harmonia.logic.commands;
//
//import static seedu.harmonia.logic.commands.CommandTestUtil.assertCommandFailure;
//import static seedu.harmonia.logic.commands.CommandTestUtil.assertCommandSuccess;
//import static seedu.harmonia.testutil.TypicalTasks.getTypicalTaskList;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import seedu.harmonia.model.Model;
//import seedu.harmonia.model.ModelManager;
//import seedu.harmonia.model.UserPrefs;
//import seedu.harmonia.model.task.Task;
//import seedu.harmonia.testutil.TaskBuilder;
//
///**
// * Contains integration tests (interaction with the Model) for {@code AddCommand}.
// */
//public class AddCommandIntegrationTest {
//
//    private Model model;
//
//    @BeforeEach
//    public void setUp() {
//        model = new ModelManager(getTypicalTaskList(), new UserPrefs());
//    }
//
//    @Test
//    public void execute_newTask_success() {
//        Task validTask = new TaskBuilder().build();
//
//        Model expectedModel = new ModelManager(model.getTaskList(), new UserPrefs());
//        expectedModel.addTask(validTask);
//
//        assertCommandSuccess(new AddCommand(validTask), model,
//                String.format(AddCommand.MESSAGE_SUCCESS, validTask), expectedModel);
//    }
//
//    @Test
//    public void execute_duplicateTask_throwsCommandException() {
//        Task taskInList = model.getTaskList().getTaskList().get(0);
//        assertCommandFailure(new AddCommand(taskInList), model, AddCommand.MESSAGE_DUPLICATE_TASK);
//    }
//
//}
