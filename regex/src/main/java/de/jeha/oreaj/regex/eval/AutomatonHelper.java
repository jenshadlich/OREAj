package de.jeha.oreaj.regex.eval;

import dk.brics.automaton.Automaton;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AutomatonHelper {

    /**
     * TODO: refactor to use iterator pattern
     *
     * @param automaton automaton
     * @param limit     limit
     * @return list of accepted words
     */
    public static List<String> accepted(Automaton automaton, int limit) {
        int len = 0;
        List<String> acc = new ArrayList<>();
        int cSize = 0;
        int cLoops = 0;

        add:
        while (true) {
            cLoops++;

            // get all accepted strings for the given size
            Set<String> curr = automaton.getStrings(cSize); // TODO: investigate why the set has a different order with Java 8

            // no Strings for the given size
            // TODO: refactor magic number
            if (curr.isEmpty() && cLoops > 1_000)
                break;

            for (String s : curr) {
                if (len < limit) {
                    acc.add(s); // add to list of accepted strings
                    len++;
                } else {
                    break add;
                }
            }
            cSize++;
        }

        return acc;
    }
}
