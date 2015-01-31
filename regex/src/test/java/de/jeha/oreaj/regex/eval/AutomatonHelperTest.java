package de.jeha.oreaj.regex.eval;

import dk.brics.automaton.Automaton;
import dk.brics.automaton.RegExp;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AutomatonHelperTest {

    @Test
    public void acceptedTestAStar() {
        Automaton a = new RegExp("a*").toAutomaton();
        String[] expected = {"", "a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa"};

        int i = 0;
        for (String s : AutomatonHelper.accepted(a, 10)) {
            assertEquals(expected[i++], s);
        }
    }

    @Test
    public void acceptedTestAOrBStar() {
        Automaton a = new RegExp("(a|b)*").toAutomaton();
        Set<String> expected = new HashSet<>(Arrays.asList(new String[]{
                "", "b", "a", "ba", "aa", "ab", "bb", "aaa", "aab", "abb", "bbb", "bba", "baa", "aba", "bab"
        }));

        for (String s : AutomatonHelper.accepted(a, 15)) {
            assertTrue(expected.contains(s));
        }
    }
}
