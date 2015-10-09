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
        List<String> accepted = new ArrayList<>();
        int i = 0;

        loop:
        do {
            final Set<String> currentBatch = automaton.getStrings(i);
            if (!currentBatch.isEmpty()) {
                for (String string : currentBatch) {
                    if (accepted.size() < limit) {
                        accepted.add(string); // add to list of accepted strings
                    } else {
                        break loop;
                    }
                }
            }
            i++;
        } while (i < MAX_LOOPS);

        return accepted;
    }

    public static boolean isEquivalent(Automaton a, Automaton b) {
        Automaton tooMuch = a.minus(b);
        Automaton missing = b.minus(a);

        return tooMuch.isEmpty() && missing.isEmpty();
    }

}
