package seedu.harmonia.logic.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

import seedu.harmonia.commons.core.index.Index;
import seedu.harmonia.logic.parser.exceptions.ParseException;

/**
 * Parses through the second part of the user input (after the command) and determine which indexes are to be called in
 * the mass operation.
 */
public class MassOpsParser {
    /**
     * Parses through the second part of user input to process what indexes are called.
     *
     * @param args string of indexes to perform mass ops on
     * @return a {@code ArrayList<Index>} with the indexes to be performing mass ops on
     * @throws ParseException
     */
    public static List<Index> massOpProcessor(String args) throws ParseException {
        List<String> stringIndexes = new ArrayList<>(Arrays.asList(args.trim().split(" ")));
        List<Index> indexes = new ArrayList<>();


        for (int i = 0; i < stringIndexes.size(); i++) {
            Index index = ParserUtil.parseIndex(stringIndexes.get(i));
            indexes.add(index);
        }

        return indexes;
    }

    /**
     * Sorts the indexes in ascending order.
     *
     * @param indexes
     * @return a {@code List<Indexes>} sorted in ascending order.
     */
    public static List<Index> sortInAsc(List<Index> indexes) {
        TreeSet<Index> sorted = new TreeSet<>(indexes);
        List<Index> ascIndexes = new ArrayList<>(sorted);
        return ascIndexes;
    }

    /**
     * Sorts the indexes in descending order.
     *
     * @param indexes
     * @return a {@code List<Indexes>} sorted in descending order.
     */
    public static List<Index> sortInDesc(List<Index> indexes) {
        List<Index> descIndexes = sortInAsc(indexes);
        Collections.reverse(descIndexes);
        return descIndexes;
    }
}
