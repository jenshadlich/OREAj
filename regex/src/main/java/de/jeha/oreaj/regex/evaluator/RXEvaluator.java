package de.jeha.oreaj.regex.evaluator;

import de.jeha.oreaj.regex.rx.RX;
import dk.brics.automaton.Automaton;
import dk.brics.automaton.RegExp;

import java.util.HashSet;
import java.util.Set;

public class RXEvaluator extends AbstractRXEvaluator {

    private final Automaton target;

    public RXEvaluator(Automaton target) {
        this.target = target;
    }

    @Override
    public double evaluate(RX rx) {
        RegExp regExp = new RegExp(rx.show());
        Automaton semantics = regExp.toAutomaton();
        Automaton tooMuch = semantics.minus(target);
        Automaton missing = target.minus(semantics);

        double weightTooMuch = 100_000 * weight(tooMuch);
        double weightMissing = 1_000 * weight(missing);

        // TODO: punish if rx has terminals that target does not

        return weightTooMuch + weightMissing;
    }

    private Set<String> getTerminalSet(RX rx) {
        Set<String> terminalSet = new HashSet<>();
        final String rxString = rx.show();
        for (int i = 0; i < rxString.length(); i++) {
            char c = rxString.charAt(i);
            if (Character.isLetter(c)) {
                terminalSet.add(Character.toString(c));
            }
        }
        return terminalSet;
    }
}
