//package seedu.harmonia.logic.commands;
//
//import static seedu.harmonia.logic.commands.CommandTestUtil.assertCommandSuccess;
//import static seedu.harmonia.testutil.TypicalTasks.getTypicalTaskList;
//
//import org.junit.jupiter.api.Test;
//
//import seedu.harmonia.model.Model;
//import seedu.harmonia.model.ModelManager;
//import seedu.harmonia.model.TaskList;
//import seedu.harmonia.model.UserPrefs;
//
//public class ClearCommandTest {
//
//    @Test
//    public void execute_emptyTaskList_success() {
//        Model model = new ModelManager();
//        Model expectedModel = new ModelManager();
//
//        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
//    }
//
//    @Test
//    public void execute_nonEmptyTaskList_success() {
//        Model model = new ModelManager(getTypicalTaskList(), new UserPrefs());
//        Model expectedModel = new ModelManager(getTypicalTaskList(), new UserPrefs());
//        expectedModel.setTaskList(new TaskList());
//
//        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
//    }
//
//}
