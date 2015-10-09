package de.jeha.oreaj.regex.automaton;

import dk.brics.automaton.Automaton;
import dk.brics.automaton.RegExp;
import dk.brics.automaton.ShuffleOperations;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ShuffleTest {

    @Test
    public void shuffle() {
        // (aa)* $ b*
        Automaton a = new RegExp("(aa)*").toAutomaton();
        Automaton b = new RegExp("b*").toAutomaton();
        Automaton shuffle = ShuffleOperations.shuffle(a, b);

        List<String> accepted = AutomatonHelper.accepted(shuffle, 10);

        System.out.println(accepted);

        assertEquals("", accepted.get(0));
    }
}
