package seedu.harmonia.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.harmonia.commons.core.Messages.MESSAGE_TASKS_LISTED_OVERVIEW;
import static seedu.harmonia.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.harmonia.testutil.TypicalTasks.CS2103T_PROJECT;
import static seedu.harmonia.testutil.TypicalTasks.CS2105_FINALS;
import static seedu.harmonia.testutil.TypicalTasks.CS2105_MIDTERM;
import static seedu.harmonia.testutil.TypicalTasks.MEET_ALICE;
import static seedu.harmonia.testutil.TypicalTasks.getTypicalTaskList;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.harmonia.model.Model;
import seedu.harmonia.model.ModelManager;
import seedu.harmonia.model.UserPrefs;
import seedu.harmonia.model.tag.TagContainsKeywordsPredicate;
import seedu.harmonia.model.task.Deadline;
import seedu.harmonia.model.task.DeadlineInRangePredicate;
import seedu.harmonia.model.task.NameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalTaskList(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalTaskList(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstNamePredicate =
                new NameContainsKeywordsPredicate(Collections.singleton("firstName"));
        NameContainsKeywordsPredicate secondNamePredicate =
                new NameContainsKeywordsPredicate(Collections.singleton("secondName"));
        TagContainsKeywordsPredicate firstTagPredicate =
                new TagContainsKeywordsPredicate(new HashSet<>(Collections.singletonList("firstTag")));
        TagContainsKeywordsPredicate secondTagPredicate =
                new TagContainsKeywordsPredicate(new HashSet<>(Collections.singletonList("secondTag")));
        DeadlineInRangePredicate firstDeadlinePredicate =
                new DeadlineInRangePredicate(new Deadline("2022-03-04"), new Deadline("2022-03-05"));
        DeadlineInRangePredicate secondDeadlinePredicate =
                new DeadlineInRangePredicate(new Deadline("2022-03-09"), new Deadline("2022-03-22"));

        FindCommand findFirstCommand =
                new FindCommand(firstNamePredicate, firstTagPredicate, firstDeadlinePredicate);
        FindCommand findSecondCommand =
                new FindCommand(secondNamePredicate, secondTagPredicate, secondDeadlinePredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy =
                new FindCommand(firstNamePredicate, firstTagPredicate, firstDeadlinePredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different task -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywordsAndVeryLargeDateRange_noTaskFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 5);
        NameContainsKeywordsPredicate namePredicate = prepareNamePredicate(" ");
        TagContainsKeywordsPredicate tagPredicate = prepareTagPredicate(" ");
        DeadlineInRangePredicate deadlinePredicate =
                new DeadlineInRangePredicate(null, null);
        FindCommand command = new FindCommand(namePredicate, tagPredicate, deadlinePredicate);
        expectedModel.updateFilteredTaskList(namePredicate.and(tagPredicate).and(deadlinePredicate));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(expectedModel.getFilteredTaskList(), model.getFilteredTaskList());
    }

    @Test
    public void execute_multipleNameKeywordsAndZeroTagKeywordsAndVeryLargeDateRange_multipleTasksFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 2);
        NameContainsKeywordsPredicate namePredicate = prepareNamePredicate("Alice Project");
        TagContainsKeywordsPredicate tagPredicate = prepareTagPredicate(" ");
        DeadlineInRangePredicate deadlinePredicate =
                new DeadlineInRangePredicate(null, null);
        FindCommand command = new FindCommand(namePredicate, tagPredicate, deadlinePredicate);
        expectedModel.updateFilteredTaskList(namePredicate.and(tagPredicate).and(deadlinePredicate));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CS2103T_PROJECT, MEET_ALICE), model.getFilteredTaskList());
    }

    @Test
    public void execute_zeroNameKeywordsAndMultipleTagKeywordsAndVeryLargeDateRange_multipleTasksFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate namePredicate = prepareNamePredicate(" ");
        TagContainsKeywordsPredicate tagPredicate = prepareTagPredicate("exam CS2103T");
        DeadlineInRangePredicate deadlinePredicate =
                new DeadlineInRangePredicate(null, null);
        FindCommand command = new FindCommand(namePredicate, tagPredicate, deadlinePredicate);
        expectedModel.updateFilteredTaskList(namePredicate.and(tagPredicate).and(deadlinePredicate));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CS2103T_PROJECT, CS2105_FINALS, CS2105_MIDTERM), model.getFilteredTaskList());
    }

    @Test
    public void execute_multipleNameKeywordsAndMultipleTagKeywordsAndVeryLargeDateRange_multipleTasksFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 1);
        NameContainsKeywordsPredicate namePredicate = prepareNamePredicate("Alice Project");
        TagContainsKeywordsPredicate tagPredicate = prepareTagPredicate("exam cs2103t");
        DeadlineInRangePredicate deadlinePredicate =
                new DeadlineInRangePredicate(null, null);
        FindCommand command = new FindCommand(namePredicate, tagPredicate, deadlinePredicate);
        expectedModel.updateFilteredTaskList(namePredicate.and(tagPredicate).and(deadlinePredicate));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(expectedModel.getFilteredTaskList(),
                model.getFilteredTaskList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate prepareNamePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Set.of(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code TagContainsKeywordsPredicate}.
     */
    private TagContainsKeywordsPredicate prepareTagPredicate(String userInput) {
        return new TagContainsKeywordsPredicate(new HashSet<>(Arrays.asList(userInput.split("\\s+"))));
    }
}
