//package seedu.harmonia.logic.parser;
//
//import static seedu.harmonia.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
//import static seedu.harmonia.logic.commands.CommandTestUtil.DEADLINE_DESC_MIDTERM;
//import static seedu.harmonia.logic.commands.CommandTestUtil.DEADLINE_DESC_TUTORIAL;
//import static seedu.harmonia.logic.commands.CommandTestUtil.DESCRIPTION_DESC_MIDTERM;
//import static seedu.harmonia.logic.commands.CommandTestUtil.DESCRIPTION_DESC_TUTORIAL;
//import static seedu.harmonia.logic.commands.CommandTestUtil.INVALID_DEADLINE_DESC;
//import static seedu.harmonia.logic.commands.CommandTestUtil.INVALID_DESCRIPTION_DESC;
//import static seedu.harmonia.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
//import static seedu.harmonia.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
//import static seedu.harmonia.logic.commands.CommandTestUtil.NAME_DESC_MIDTERM;
//import static seedu.harmonia.logic.commands.CommandTestUtil.NAME_DESC_TUTORIAL;
//import static seedu.harmonia.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
//import static seedu.harmonia.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
//import static seedu.harmonia.logic.commands.CommandTestUtil.TAG_DESC_CS2102;
//import static seedu.harmonia.logic.commands.CommandTestUtil.TAG_DESC_TEST;
//import static seedu.harmonia.logic.commands.CommandTestUtil.VALID_DEADLINE_MIDTERM;
//import static seedu.harmonia.logic.commands.CommandTestUtil.VALID_DESCRIPTION_MIDTERM;
//import static seedu.harmonia.logic.commands.CommandTestUtil.VALID_NAME_MIDTERM;
//import static seedu.harmonia.logic.commands.CommandTestUtil.VALID_TAG_CS2102;
//import static seedu.harmonia.logic.commands.CommandTestUtil.VALID_TAG_TEST;
//import static seedu.harmonia.logic.parser.CommandParserTestUtil.assertParseFailure;
//import static seedu.harmonia.logic.parser.CommandParserTestUtil.assertParseSuccess;
//import static seedu.harmonia.testutil.TypicalTasks.CS2102_MIDTERM;
//
//import org.junit.jupiter.api.Test;
//
//import seedu.harmonia.logic.commands.AddCommand;
//import seedu.harmonia.model.tag.Tag;
//import seedu.harmonia.model.task.Deadline;
//import seedu.harmonia.model.task.Description;
//import seedu.harmonia.model.task.Name;
//import seedu.harmonia.model.task.Task;
//import seedu.harmonia.testutil.TaskBuilder;
//
//public class AddCommandParserTest {
//    private AddCommandParser parser = new AddCommandParser();
//
//    @Test
//    public void parse_allFieldsPresent_success() {
//
//        Task expectedTask = new TaskBuilder(CS2102_MIDTERM).withTags(VALID_TAG_TEST).build();
//
//        // whitespace only preamble
//        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_MIDTERM
//                + DESCRIPTION_DESC_MIDTERM + DEADLINE_DESC_MIDTERM
//                + TAG_DESC_TEST, new AddCommand(expectedTask));
//
//        // multiple names - last name accepted
//        assertParseSuccess(parser, NAME_DESC_TUTORIAL + NAME_DESC_MIDTERM
//                + DESCRIPTION_DESC_MIDTERM + DEADLINE_DESC_MIDTERM
//                + TAG_DESC_TEST , new AddCommand(expectedTask));
//
//        // multiple descriptions - last description accepted
//        assertParseSuccess(parser, NAME_DESC_MIDTERM + DESCRIPTION_DESC_TUTORIAL
//                + DESCRIPTION_DESC_MIDTERM + DEADLINE_DESC_MIDTERM
//                + TAG_DESC_TEST , new AddCommand(expectedTask));
//
//        // multiple deadlines - last deadline accepted
//        assertParseSuccess(parser,  NAME_DESC_MIDTERM + DESCRIPTION_DESC_MIDTERM
//                + DEADLINE_DESC_TUTORIAL + DEADLINE_DESC_MIDTERM
//                + TAG_DESC_TEST , new AddCommand(expectedTask));
//
//        // multiple tags - all accepted
//        Task expectedTaskMultipleTags = new TaskBuilder(CS2102_MIDTERM).withTags(VALID_TAG_TEST, VALID_TAG_CS2102)
//                .build();
//        assertParseSuccess(parser,  NAME_DESC_MIDTERM
//                + DESCRIPTION_DESC_MIDTERM + DEADLINE_DESC_MIDTERM + TAG_DESC_TEST
//                + TAG_DESC_CS2102, new AddCommand(expectedTaskMultipleTags));
//    }
//
//    /**
//     * This method is commented out now, as there's no optional fields at this point.
//     */
////    @Test
////    public void parse_optionalFieldsMissing_success() {
////        // zero tags
////        Person expectedPerson = new PersonBuilder(AMY).withTags().build();
////        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY,
////                new AddCommand(expectedPerson));
////    }
//
//    @Test
//    public void parse_compulsoryFieldMissing_failure() {
//        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);
//
//        // missing name prefix
//        assertParseFailure(parser, VALID_NAME_MIDTERM + DESCRIPTION_DESC_MIDTERM + DEADLINE_DESC_MIDTERM,
//                expectedMessage);
//
//        // missing description prefix
//        assertParseFailure(parser, NAME_DESC_MIDTERM + VALID_DESCRIPTION_MIDTERM + DEADLINE_DESC_MIDTERM,
//                expectedMessage);
//
//        // missing deadline prefix
//        assertParseFailure(parser, NAME_DESC_MIDTERM + DESCRIPTION_DESC_MIDTERM + VALID_DEADLINE_MIDTERM,
//                expectedMessage);
//
//        // all prefixes missing
//        assertParseFailure(parser, VALID_NAME_MIDTERM + VALID_DESCRIPTION_MIDTERM + VALID_DEADLINE_MIDTERM,
//                expectedMessage);
//    }
//
//    @Test
//    public void parse_invalidValue_failure() {
//        // invalid name
//        assertParseFailure(parser, INVALID_NAME_DESC + DESCRIPTION_DESC_MIDTERM + DEADLINE_DESC_MIDTERM
//                + TAG_DESC_TEST + TAG_DESC_CS2102, Name.MESSAGE_CONSTRAINTS);
//
//        // invalid description
//        assertParseFailure(parser, NAME_DESC_MIDTERM + INVALID_DESCRIPTION_DESC + DEADLINE_DESC_MIDTERM
//                + TAG_DESC_TEST + TAG_DESC_CS2102, Description.MESSAGE_CONSTRAINTS);
//
//        // invalid deadline
//        assertParseFailure(parser, NAME_DESC_MIDTERM + DESCRIPTION_DESC_MIDTERM + INVALID_DEADLINE_DESC
//                + TAG_DESC_TEST + TAG_DESC_CS2102, Deadline.MESSAGE_CONSTRAINTS);
//
//        // invalid tag
//        assertParseFailure(parser, NAME_DESC_MIDTERM + DESCRIPTION_DESC_MIDTERM + DEADLINE_DESC_MIDTERM
//                + INVALID_TAG_DESC + TAG_DESC_CS2102, Tag.MESSAGE_CONSTRAINTS);
//
//        // two invalid values, only first invalid value reported
//        assertParseFailure(parser, INVALID_NAME_DESC + DESCRIPTION_DESC_MIDTERM + INVALID_DEADLINE_DESC
//                + TAG_DESC_TEST + TAG_DESC_CS2102, Name.MESSAGE_CONSTRAINTS);
//
//        // non-empty preamble
//        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_MIDTERM + DESCRIPTION_DESC_MIDTERM
//                + DEADLINE_DESC_MIDTERM + TAG_DESC_TEST + TAG_DESC_CS2102,
//                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
//    }
//}
