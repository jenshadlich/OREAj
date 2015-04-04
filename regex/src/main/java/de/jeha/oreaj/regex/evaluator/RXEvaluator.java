package de.jeha.oreaj.regex.evaluator;

import de.jeha.oreaj.regex.rx.RX;
import dk.brics.automaton.Automaton;
import dk.brics.automaton.RegExp;

public class RXEvaluator extends AbstractRXEvaluator {

    private final Automaton target;

    public RXEvaluator(Automaton target) {
        this.target = target;
    }

    @Override
    public double evaluate(RX individual) {
        RegExp rx = new RegExp(individual.show());
        Automaton semantics = rx.toAutomaton();

        Automaton tooMuch = semantics.minus(target);
        Automaton missing = target.minus(semantics);

        double weightTooMuch = 100_000 * weight(tooMuch);
        double weightMissing = 1_000 * weight(missing);

        return weightTooMuch + weightMissing;
    }

}
