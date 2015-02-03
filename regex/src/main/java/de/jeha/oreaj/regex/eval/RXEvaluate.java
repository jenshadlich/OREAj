package de.jeha.oreaj.regex.eval;

import de.jeha.oreaj.genetic.Evaluator;
import de.jeha.oreaj.regex.rx.RX;
import dk.brics.automaton.Automaton;
import dk.brics.automaton.RegExp;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RXEvaluate implements Evaluator<RX> {

    private final Automaton target;

    // private final Logger LOG = LoggerFactory.getLogger(RXEvaluate.class);

    public RXEvaluate(Automaton target) {
        this.target = target;
    }

    @Override
    public double evaluate(RX individual) {
        return evaluate7(individual);
    }

    double evaluate6(Automaton target, RX individual) {

        RegExp rx = new RegExp(individual.show());
        Automaton semantics = rx.toAutomaton();

        Automaton tooMuch = semantics.minus(target);
        Automaton missing = target.minus(semantics);

        double weightTooMuch = 100_000 * weight(tooMuch);
        double weightMissing = 1_000 * weight(missing);

        return weightTooMuch + weightMissing;
    }

    double evaluate7(RX individual) {

        RegExp rx = new RegExp(individual.show());
        Automaton semantics = rx.toAutomaton();

        Automaton tooMuch = semantics.minus(target);
        Automaton missing = target.minus(semantics);

        double weightTooMuch = 100_000 * weight(tooMuch);
        double weightMissing = 1_000 * weight(missing);

        return weightTooMuch + weightMissing + individual.show().length() * 0.1;
    }

    double weight(Automaton automaton) {
        double sum = 0.0;

        for (String accepted : AutomatonHelper.accepted(automaton, 10)) {
            sum += Math.pow(2, -1 * accepted.length());
        }
        return sum;
    }
}
