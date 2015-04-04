package de.jeha.oreaj.regex.evaluator;

import de.jeha.oreaj.genetic.core.Evaluator;
import de.jeha.oreaj.regex.rx.Letter;
import de.jeha.oreaj.regex.rx.RX;
import dk.brics.automaton.RegExp;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RXEvaluatorTest {

    @Test
    public void test() {
        Evaluator<RX> evaluator = new RXEvaluator(new RegExp("(a|b)*").toAutomaton());
        double result = evaluator.evaluate(new Letter("a"));

        assertEquals(3_000, result, 1.0e-3);
    }

}
