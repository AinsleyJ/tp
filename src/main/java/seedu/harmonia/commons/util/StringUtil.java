package seedu.harmonia.commons.util;

import static java.util.Objects.requireNonNull;
import static seedu.harmonia.commons.util.AppUtil.checkArgument;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;

/**
 * Helper functions for handling strings.
 */
public class StringUtil {

    public static final String EMPTY_KEYWORD_MESSAGE = "A keyword can't be empty!";
    public static final String INVALID_KEYWORD_MESSAGE = "Only alphanumeric characters (letters and digits) are"
            + " allowed in a keyword, and a keyword should be no longer than 63 characters.";

    /*
     * The first character of the name must not be a whitespace,
     * and only alphanumeric characters are allowed.
     * The maximum number of characters allowed is 63.
     */
    private static final String KEYWORD_VALIDATION_REGEX = "\\p{Alnum}{1,63}";

    /**
     * Returns true if the {@code sentence} contains the {@code word}.
     *   Ignores case, but a full word match is required.
     *   <br>examples:<pre>
     *       containsWordIgnoreCase("ABc def", "abc") == true
     *       containsWordIgnoreCase("ABc def", "DEF") == true
     *       containsWordIgnoreCase("ABc def", "AB") == false //not a full word match
     *       </pre>
     * @param sentence cannot be null
     * @param word cannot be null, cannot be empty, must be a single word
     */
    public static boolean containsWordIgnoreCase(String sentence, String word) {
        requireNonNull(sentence);
        requireNonNull(word);

        String preppedWord = word.trim();
        checkArgument(!preppedWord.isEmpty(), "Word parameter cannot be empty");
        checkArgument(preppedWord.split("\\s+").length == 1, "Word parameter should be a single word");

        String preppedSentence = sentence;
        String[] wordsInPreppedSentence = preppedSentence.split("\\s+");

        return Arrays.stream(wordsInPreppedSentence)
                .anyMatch(preppedWord::equalsIgnoreCase);
    }

    /**
     * Returns true if the {@code sentence} contains the {@code word}.
     *   Ignores case and punctuations, but a full match is required. However, if a word is separated by punctuation(s),
     *   it is split by the punctuations.
     *   <br>examples:<pre>
     *       containsWordIgnorePunctuationAndCase("ABc def", "abc") == true
     *       containsWordIgnorePunctuationAndCase("ABc, def", "abc") == true
     *       containsWordIgnorePunctuationAndCase("ABc,def", "abc") == true
     *       containsWordIgnorePunctuationAndCase("ABc-def", "abcdef") == false // "ABc-def" is split into two words
     *       containsWordIgnorePunctuationAndCase("ABc def", "AB") == false //not a full word match
     *       </pre>
     * @param sentence cannot be null
     * @param word cannot be null, cannot be empty, must be a single word without punctuation(s)
     */
    public static boolean containsWordIgnorePunctuationAndCase(String sentence, String word) {
        requireNonNull(sentence);
        validateKeyword(word);

        String preppedWord = word.trim();
        return Arrays.stream(sentence.split("[^\\p{Alnum}]+")).anyMatch(preppedWord::equalsIgnoreCase);
    }

    /**
     * Validates a keyword. A keyword should be alphanumeric, and its length should be 1 to 63.
     *
     * @param keyword the keyword to be checked
     */
    private static void validateKeyword(String keyword) {
        requireNonNull(keyword);
        String trimmedKeyword = keyword.trim();

        // If the regex is matched, it ensures that there's only one keyword.
        checkArgument(trimmedKeyword.matches(KEYWORD_VALIDATION_REGEX), INVALID_KEYWORD_MESSAGE);
    }


    /**
     * Returns a detailed message of the t, including the stack trace.
     */
    public static String getDetails(Throwable t) {
        requireNonNull(t);
        StringWriter sw = new StringWriter();
        t.printStackTrace(new PrintWriter(sw));
        return t.getMessage() + "\n" + sw.toString();
    }

    /**
     * Returns true if {@code s} represents a non-zero unsigned integer
     * e.g. 1, 2, 3, ..., {@code Integer.MAX_VALUE} <br>
     * Will return false for any other non-null string input
     * e.g. empty string, "-1", "0", "+1", and " 2 " (untrimmed), "3 0" (contains whitespace), "1 a" (contains letters)
     * @throws NullPointerException if {@code s} is null.
     */
    public static boolean isNonZeroUnsignedInteger(String s) {
        requireNonNull(s);

        try {
            int value = Integer.parseInt(s);
            return value > 0 && !s.startsWith("+"); // "+1" is successfully parsed by Integer#parseInt(String)
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}
