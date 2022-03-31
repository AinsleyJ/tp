package seedu.harmonia.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.harmonia.commons.core.index.Index;
import seedu.harmonia.commons.util.StringUtil;
import seedu.harmonia.logic.parser.exceptions.ParseException;
import seedu.harmonia.model.tag.Tag;
import seedu.harmonia.model.task.Deadline;
import seedu.harmonia.model.task.Description;
import seedu.harmonia.model.task.Name;
import seedu.harmonia.model.task.Priority;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }


    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses a {@code String description} into a {@code Description}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code description} is invalid.
     */
    public static Description parseDescription(String description) throws ParseException {
        requireNonNull(description);
        String trimmedDescription = description.trim();
        if (!Description.isValidDescription(trimmedDescription)) {
            throw new ParseException(Description.MESSAGE_CONSTRAINTS);
        }
        return new Description(trimmedDescription);
    }

    /**
     * Parses a {@code String priority} into a {@code Priority}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code description} is invalid.
     */
    public static Priority parsePriority(String priority) throws ParseException {
        requireNonNull(priority);
        String trimmedPriority = priority.trim();
        String lowercasePriority = trimmedPriority.toLowerCase();
        if (!Priority.isValidPriority(lowercasePriority)) {
            throw new ParseException(Priority.MESSAGE_CONSTRAINTS);
        }

        return Priority.valueOfLabel(lowercasePriority);
    }

    /**
     * Parses {@code Collection<String> priorities} into a {@code Set<Priority>}.
     */
    public static Set<Priority> parsePriorities(Collection<String> priorities) throws ParseException {
        requireNonNull(priorities);
        final Set<Priority> prioritySet = new HashSet<>();
        for (String priority : priorities) {
            prioritySet.add(parsePriority(priority));
        }
        return prioritySet;
    }

    /**
     * Parses a {@code String deadline} into a {@code Deadline}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code deadline} is invalid.
     */
    public static Deadline parseDeadline(String deadline) throws ParseException {
        requireNonNull(deadline);
        String trimmedDeadline = deadline.trim();
        if (!Deadline.isValidDeadline(trimmedDeadline)) {
            throw new ParseException(Deadline.MESSAGE_CONSTRAINTS);
        }
        return new Deadline(trimmedDeadline);
    }

    /**
     * Parses {@code Collection<String> keywords} into a {@code Set<String>}.
     */
    public static Set<String> parseKeywords(Collection<String> keywords) throws ParseException {
        requireNonNull(keywords);
        final Set<String> keywordSet = new HashSet<>();
        for (String keyword : keywords) {
            if (keyword.isEmpty()) {
                throw new ParseException("A keyword can't be empty!");
            }
            if (keyword.split("\\s+").length > 1) {
                throw new ParseException("A keyword can't contain any whitespace!");
            }
            keywordSet.add(keyword);
        }
        return keywordSet;
    }
}
