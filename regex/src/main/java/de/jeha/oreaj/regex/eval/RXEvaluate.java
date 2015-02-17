package de.jeha.oreaj.regex.eval;

import de.jeha.oreaj.genetic.Evaluator;
import de.jeha.oreaj.regex.automaton.AutomatonHelper;
import de.jeha.oreaj.regex.rx.RX;
import dk.brics.automaton.Automaton;
import dk.brics.automaton.RegExp;

public class RXEvaluate implements Evaluator<RX> {

    public static final int DEFAULT_AUTOMATON_ACCEPT_LIMIT = 10;

    private final Automaton target;
    private final int automatonAcceptLimit;

    // private static final Logger LOG = LoggerFactory.getLogger(RXEvaluate.class);

    public RXEvaluate(Automaton target) {
        this.target = target;
        this.automatonAcceptLimit = DEFAULT_AUTOMATON_ACCEPT_LIMIT;
    }

    @Override
    public double evaluate(RX individual) {
        return evaluate7(individual);
    }

    double evaluate6(RX individual) {

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

        for (String accepted : AutomatonHelper.accepted(automaton, automatonAcceptLimit)) {
            sum += Math.pow(2, -1 * accepted.length());
        }
        return sum;
    }
}
