package de.jeha.oreaj.regex.evaluator;

import de.jeha.oreaj.genetic.core.Evaluator;
import de.jeha.oreaj.regex.parse.NoParseException;
import de.jeha.oreaj.regex.parse.RegexParser;
import de.jeha.oreaj.regex.rx.Letter;
import de.jeha.oreaj.regex.rx.RX;
import dk.brics.automaton.RegExp;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RXEvaluatorWithLengthTest {

    @Test
    void test() {
        Evaluator<RX> evaluator = new RXEvaluatorWithLength(new RegExp("(a|b)*").toAutomaton());
        double result = evaluator.evaluate(new Letter("a"));

        assertEquals(3_000.1, result, 1.0e-3);
    }

    @Test
    void test2() throws NoParseException {
        Evaluator<RX> evaluator = new RXEvaluatorWithLength(new RegExp("(aa|ba)*").toAutomaton());
        double result = evaluator.evaluate(new RegexParser("((a)*)*").parse());

        assertEquals(67_198.553, result, 1.0e-3);

    }

    @Test
    void test3() throws NoParseException {
        Evaluator<RX> evaluator = new RXEvaluatorWithLength(new RegExp("(aa|ba)*").toAutomaton());
        double result = evaluator.evaluate(new RegexParser("(a(a)*|(a)*)*").parse());

        assertEquals(67_199.153, result, 1.0e-3);
    }

}
