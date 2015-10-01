package de.jeha.oreaj.regex.automaton;

import dk.brics.automaton.Automaton;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AutomatonHelper {

    private static final int MAX_LOOPS = 1_000; // TODO: make magic number configurable?

    /**
     * TODO: refactor to use iterator pattern or Java 8 stream
     *
     * @param automaton automaton
     * @param limit     limit
     * @return list of accepted words
     */
    public static List<String> accepted(Automaton automaton, int limit) {
        int length = 0;
        List<String> accepted = new ArrayList<>();
        int fetchLength = 0;
        int cLoops = 0;

        add:
        while (true) {
            cLoops++;

            // get all accepted strings of the given length
            final Set<String> currentBatch = automaton.getStrings(fetchLength);

            // no Strings for the given size
            if (currentBatch.isEmpty() && cLoops > MAX_LOOPS) {
                break;
            }

            for (String string : currentBatch) {
                if (length < limit) {
                    accepted.add(string); // add to list of accepted strings
                    length++;
                } else {
                    break add;
                }
            }

            fetchLength++;
        }

        return accepted;
    }

    public static boolean isEquivalent(Automaton a, Automaton b) {
        Automaton tooMuch = a.minus(b);
        Automaton missing = b.minus(a);

        return tooMuch.isEmpty() && missing.isEmpty();
    }

}
