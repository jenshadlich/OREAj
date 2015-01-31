package de.jeha.oreaj.regex.eval;

import de.jeha.oreaj.regex.rx.Letter;
import dk.brics.automaton.RegExp;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RXEvaluateTest {

    @Test
    public void testEvaluate6() {
        RXEvaluate evaluate = new RXEvaluate(new RegExp("(a|b)*").toAutomaton());
        double result = evaluate.evaluate7(new Letter("a"));

        assertEquals(3000.1, result, 1.0e-3);
    }
}
