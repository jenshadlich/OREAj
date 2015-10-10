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
     * @param automaton   automaton
     * @param maxAccepted maximum number of accepted words
     * @return list of accepted words
     */
    public static List<String> accepted(Automaton automaton, int maxAccepted) {
        List<String> accepted = new ArrayList<>();

        for (int i = 0; i < MAX_LOOPS; i++) {
            final Set<String> currentBatch = automaton.getStrings(i);
            if (!currentBatch.isEmpty()) {
                for (String string : currentBatch) {
                    if (accepted.size() < maxAccepted) {
                        accepted.add(string); // add to list of accepted strings
                    } else {
                        return accepted;
                    }
                }
            }
        }

        return accepted;
    }

    public static boolean isEquivalent(Automaton a, Automaton b) {
        Automaton tooMuch = a.minus(b);
        Automaton missing = b.minus(a);

        return tooMuch.isEmpty() && missing.isEmpty();
    }

}
