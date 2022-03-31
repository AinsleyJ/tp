//package seedu.harmonia.logic.commands;
//
//import static seedu.harmonia.logic.commands.CommandTestUtil.assertCommandSuccess;
//import static seedu.harmonia.logic.commands.ExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT;
//
//import org.junit.jupiter.api.Test;
//
//import seedu.harmonia.model.Model;
//import seedu.harmonia.model.ModelManager;
//
//public class ExitCommandTest {
//    private Model model = new ModelManager();
//    private Model expectedModel = new ModelManager();
//
//    @Test
//    public void execute_exit_success() {
//        CommandResult expectedCommandResult = new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
//        assertCommandSuccess(new ExitCommand(), model, expectedCommandResult, expectedModel);
//    }
//}
