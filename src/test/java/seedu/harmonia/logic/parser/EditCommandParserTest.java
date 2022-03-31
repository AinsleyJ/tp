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
//import static seedu.harmonia.logic.commands.CommandTestUtil.NAME_DESC_TUTORIAL;
//import static seedu.harmonia.logic.commands.CommandTestUtil.TAG_DESC_CS2103T;
//import static seedu.harmonia.logic.commands.CommandTestUtil.TAG_DESC_TEST;
//import static seedu.harmonia.logic.commands.CommandTestUtil.VALID_DEADLINE_MIDTERM;
//import static seedu.harmonia.logic.commands.CommandTestUtil.VALID_DEADLINE_TUTORIAL;
//import static seedu.harmonia.logic.commands.CommandTestUtil.VALID_DESCRIPTION_MIDTERM;
//import static seedu.harmonia.logic.commands.CommandTestUtil.VALID_DESCRIPTION_TUTORIAL;
//import static seedu.harmonia.logic.commands.CommandTestUtil.VALID_NAME_TUTORIAL;
//import static seedu.harmonia.logic.commands.CommandTestUtil.VALID_TAG_CS2103T;
//import static seedu.harmonia.logic.commands.CommandTestUtil.VALID_TAG_TEST;
//import static seedu.harmonia.logic.parser.CliSyntax.PREFIX_TAG;
//import static seedu.harmonia.logic.parser.CommandParserTestUtil.assertParseFailure;
//import static seedu.harmonia.logic.parser.CommandParserTestUtil.assertParseSuccess;
//import static seedu.harmonia.testutil.TypicalIndexes.INDEX_FIRST_TASK;
//import static seedu.harmonia.testutil.TypicalIndexes.INDEX_SECOND_TASK;
//import static seedu.harmonia.testutil.TypicalIndexes.INDEX_THIRD_TASK;
//
//import org.junit.jupiter.api.Test;
//
//import seedu.harmonia.commons.core.index.Index;
//import seedu.harmonia.logic.commands.EditCommand;
//import seedu.harmonia.logic.commands.EditCommand.EditTaskDescriptor;
//import seedu.harmonia.model.tag.Tag;
//import seedu.harmonia.model.task.Deadline;
//import seedu.harmonia.model.task.Description;
//import seedu.harmonia.model.task.Name;
//import seedu.harmonia.testutil.EditTaskDescriptorBuilder;
//
//public class EditCommandParserTest {
//
//    private static final String TAG_EMPTY = " " + PREFIX_TAG;
//
//    private static final String MESSAGE_INVALID_FORMAT =
//            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);
//
//    private EditCommandParser parser = new EditCommandParser();
//
//    @Test
//    public void parse_missingParts_failure() {
//        // no index specified
//        assertParseFailure(parser, VALID_NAME_TUTORIAL, MESSAGE_INVALID_FORMAT);
//
//        // no field specified
//        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);
//
//        // no index and no field specified
//        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
//    }
//
//    @Test
//    public void parse_invalidPreamble_failure() {
//        // negative index
//        assertParseFailure(parser, "-5" + NAME_DESC_TUTORIAL, MESSAGE_INVALID_FORMAT);
//
//        // zero index
//        assertParseFailure(parser, "0" + NAME_DESC_TUTORIAL, MESSAGE_INVALID_FORMAT);
//
//        // invalid arguments being parsed as preamble
//        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);
//
//        // invalid prefix being parsed as preamble
//        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
//    }
//
//    @Test
//    public void parse_invalidValue_failure() {
//        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
//        assertParseFailure(
//                parser, "1" + INVALID_DESCRIPTION_DESC, Description.MESSAGE_CONSTRAINTS); // invalid description
//        assertParseFailure(parser, "1" + INVALID_DEADLINE_DESC, Deadline.MESSAGE_CONSTRAINTS); // invalid deadline
//        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag
//
//        // invalid description followed by valid deadline
//        assertParseFailure(
//                parser, "1" + INVALID_DESCRIPTION_DESC + DEADLINE_DESC_TUTORIAL, Description.MESSAGE_CONSTRAINTS);
//
//        // valid description followed by invalid description. The test case for invalid description followed by
//        // valid description is tested at {@code parse_invalidValueFollowedByValidValue_success()}
//        assertParseFailure(
//                parser, "1" + DESCRIPTION_DESC_MIDTERM + INVALID_DESCRIPTION_DESC, Description.MESSAGE_CONSTRAINTS);
//
//        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Task} being edited,
//        // parsing it together with a valid tag results in error
//        assertParseFailure(parser, "1" + TAG_DESC_CS2103T + TAG_DESC_TEST + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
//        assertParseFailure(parser, "1" + TAG_DESC_CS2103T + TAG_EMPTY + TAG_DESC_TEST, Tag.MESSAGE_CONSTRAINTS);
//        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_CS2103T + TAG_DESC_TEST, Tag.MESSAGE_CONSTRAINTS);
//
//        // multiple invalid values, but only the first invalid value is captured
//        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_DEADLINE_DESC + VALID_DESCRIPTION_TUTORIAL,
//                Name.MESSAGE_CONSTRAINTS);
//    }
//
//    @Test
//    public void parse_allFieldsSpecified_success() {
//        Index targetIndex = INDEX_SECOND_TASK;
//        String userInput = targetIndex.getOneBased() + DESCRIPTION_DESC_MIDTERM + TAG_DESC_TEST
//                + DEADLINE_DESC_TUTORIAL + NAME_DESC_TUTORIAL + TAG_DESC_CS2103T;
//
//        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withName(VALID_NAME_TUTORIAL)
//                .withDescription(VALID_DESCRIPTION_MIDTERM).withDeadline(VALID_DEADLINE_TUTORIAL)
//                .withTags(VALID_TAG_CS2103T, VALID_TAG_TEST).build();
//        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
//
//        assertParseSuccess(parser, userInput, expectedCommand);
//    }
//
//    @Test
//    public void parse_someFieldsSpecified_success() {
//        Index targetIndex = INDEX_FIRST_TASK;
//        String userInput = targetIndex.getOneBased() + DESCRIPTION_DESC_MIDTERM + DEADLINE_DESC_TUTORIAL;
//
//        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withDescription(VALID_DESCRIPTION_MIDTERM)
//                .withDeadline(VALID_DEADLINE_TUTORIAL).build();
//        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
//
//        assertParseSuccess(parser, userInput, expectedCommand);
//    }
//
//    @Test
//    public void parse_oneFieldSpecified_success() {
//        // name
//        Index targetIndex = INDEX_THIRD_TASK;
//        String userInput = targetIndex.getOneBased() + NAME_DESC_TUTORIAL;
//        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withName(VALID_NAME_TUTORIAL).build();
//        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
//        assertParseSuccess(parser, userInput, expectedCommand);
//
//        // description
//        userInput = targetIndex.getOneBased() + DESCRIPTION_DESC_TUTORIAL;
//        descriptor = new EditTaskDescriptorBuilder().withDescription(VALID_DESCRIPTION_TUTORIAL).build();
//        expectedCommand = new EditCommand(targetIndex, descriptor);
//        assertParseSuccess(parser, userInput, expectedCommand);
//
//        // deadline
//        userInput = targetIndex.getOneBased() + DEADLINE_DESC_TUTORIAL;
//        descriptor = new EditTaskDescriptorBuilder().withDeadline(VALID_DEADLINE_TUTORIAL).build();
//        expectedCommand = new EditCommand(targetIndex, descriptor);
//        assertParseSuccess(parser, userInput, expectedCommand);
//
//        // tags
//        userInput = targetIndex.getOneBased() + TAG_DESC_CS2103T;
//        descriptor = new EditTaskDescriptorBuilder().withTags(VALID_TAG_CS2103T).build();
//        expectedCommand = new EditCommand(targetIndex, descriptor);
//        assertParseSuccess(parser, userInput, expectedCommand);
//    }
//
//    @Test
//    public void parse_multipleRepeatedFields_acceptsLast() {
//        Index targetIndex = INDEX_FIRST_TASK;
//        String userInput = targetIndex.getOneBased() + DESCRIPTION_DESC_TUTORIAL + DEADLINE_DESC_TUTORIAL
//                + TAG_DESC_CS2103T + DESCRIPTION_DESC_TUTORIAL + DEADLINE_DESC_TUTORIAL + TAG_DESC_CS2103T
//                + DESCRIPTION_DESC_MIDTERM + DEADLINE_DESC_MIDTERM + TAG_DESC_TEST;
//
//        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withDescription(VALID_DESCRIPTION_MIDTERM)
//                .withDeadline(VALID_DEADLINE_MIDTERM).withTags(VALID_TAG_CS2103T, VALID_TAG_TEST)
//                .build();
//        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
//
//        assertParseSuccess(parser, userInput, expectedCommand);
//    }
//
//    @Test
//    public void parse_invalidValueFollowedByValidValue_success() {
//        // no other valid values specified
//        Index targetIndex = INDEX_FIRST_TASK;
//        String userInput = targetIndex.getOneBased() + INVALID_DESCRIPTION_DESC + DESCRIPTION_DESC_MIDTERM;
//        EditTaskDescriptor descriptor =
//                new EditTaskDescriptorBuilder().withDescription(VALID_DESCRIPTION_MIDTERM).build();
//        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
//        assertParseSuccess(parser, userInput, expectedCommand);
//
//        // other valid values specified
//        userInput = targetIndex.getOneBased() + DEADLINE_DESC_MIDTERM + INVALID_DESCRIPTION_DESC
//                + DESCRIPTION_DESC_MIDTERM;
//        descriptor = new EditTaskDescriptorBuilder().withDescription(VALID_DESCRIPTION_MIDTERM)
//                .withDeadline(VALID_DEADLINE_MIDTERM).build();
//        expectedCommand = new EditCommand(targetIndex, descriptor);
//        assertParseSuccess(parser, userInput, expectedCommand);
//    }
//
//    @Test
//    public void parse_resetTags_success() {
//        Index targetIndex = INDEX_THIRD_TASK;
//        String userInput = targetIndex.getOneBased() + TAG_EMPTY;
//
//        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withTags().build();
//        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
//
//        assertParseSuccess(parser, userInput, expectedCommand);
//    }
//}
