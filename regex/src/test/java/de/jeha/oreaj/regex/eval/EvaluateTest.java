package de.jeha.oreaj.regex.eval;

import dk.brics.automaton.Automaton;
import dk.brics.automaton.RegExp;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EvaluateTest {

    @Test
    public void acceptedTestAStar() {
        Automaton a = new RegExp("a*").toAutomaton();
        String[] expected = {"", "a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa"};

        int i = 0;
        for (String s : RXEvaluate.accepted(a, 10)) {
            assertEquals(expected[i++], s);
        }
    }

    @Test
    public void acceptedTestAOrBStar() {
        Automaton a = new RegExp("(a|b)*").toAutomaton();
        String[] expected = {"", "b", "a", "ba", "aa", "ab", "bb", "aba", "aab", "abb"};

        int i = 0;
        for (String s : RXEvaluate.accepted(a, 10)) {
            assertEquals(expected[i++], s);
        }
    }
}