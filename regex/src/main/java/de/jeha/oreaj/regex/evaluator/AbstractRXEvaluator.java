package de.jeha.oreaj.regex.evaluator;

import de.jeha.oreaj.genetic.core.Evaluator;
import de.jeha.oreaj.regex.automaton.AutomatonHelper;
import de.jeha.oreaj.regex.rx.RX;
import dk.brics.automaton.Automaton;

public abstract class AbstractRXEvaluator implements Evaluator<RX> {

    public static final int DEFAULT_AUTOMATON_ACCEPT_LIMIT = 10;

    private final int automatonAcceptLimit;

    public AbstractRXEvaluator() {
        this.automatonAcceptLimit = DEFAULT_AUTOMATON_ACCEPT_LIMIT;
    }

    protected double weight(Automaton automaton) {
        double sum = 0.0;

        for (String accepted : AutomatonHelper.accepted(automaton, automatonAcceptLimit)) {
            sum += Math.pow(2, -1 * accepted.length());
        }
        return sum;
    }
}
