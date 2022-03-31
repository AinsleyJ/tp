//package seedu.harmonia.logic.commands;
//
//import static seedu.harmonia.logic.commands.CommandTestUtil.assertCommandSuccess;
//import static seedu.harmonia.logic.commands.CommandTestUtil.showTaskAtIndex;
//import static seedu.harmonia.testutil.TypicalIndexes.INDEX_FIRST_TASK;
//import static seedu.harmonia.testutil.TypicalTasks.getTypicalTaskList;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import seedu.harmonia.model.Model;
//import seedu.harmonia.model.ModelManager;
//import seedu.harmonia.model.UserPrefs;
//
///**
// * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
// */
//public class ListCommandTest {
//
//    private Model model;
//    private Model expectedModel;
//
//    @BeforeEach
//    public void setUp() {
//        model = new ModelManager(getTypicalTaskList(), new UserPrefs());
//        expectedModel = new ModelManager(model.getTaskList(), new UserPrefs());
//    }
//
//    @Test
//    public void execute_listIsNotFiltered_showsSameList() {
//        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
//    }
//
//    @Test
//    public void execute_listIsFiltered_showsEverything() {
//        showTaskAtIndex(model, INDEX_FIRST_TASK);
//        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
//    }
//}
