package de.jeha.oreaj.regex.evaluator;

import de.jeha.oreaj.regex.rx.RX;
import dk.brics.automaton.Automaton;
import dk.brics.automaton.RegExp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RXEvaluatorWithLength extends AbstractRXEvaluator {

    private static final Logger LOG = LoggerFactory.getLogger(RXEvaluatorWithLength.class);

    private final Automaton target;

    public RXEvaluatorWithLength(Automaton target) {
        this.target = target;
    }

    @Override
    public double evaluate(RX individual) {
        RegExp regExp = new RegExp(individual.show());
        Automaton semantics = regExp.toAutomaton();

        Automaton tooMuch = semantics.minus(target);
        Automaton missing = target.minus(semantics);

        double weightTooMuch = 100_000 * weight(tooMuch);
        double weightMissing = 1_000 * weight(missing);

        final double result = weightTooMuch + weightMissing + individual.show().length() * 0.1;

        LOG.trace("{} -> {}", individual.show(), result);

        return result;
    }

}
