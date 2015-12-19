package de.jeha.oreaj.main;

import de.jeha.oreaj.genetic.core.Population;
import de.jeha.oreaj.regex.automaton.AutomatonHelper;
import de.jeha.oreaj.regex.rx.RX;
import dk.brics.automaton.Automaton;
import dk.brics.automaton.RegExp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Optimization of Regular Expressions using Evolutionary Algorithms. Main program.
 *
 * @author jenshadlich@googlemail.com
 */
public class Orea {

    private static final Logger LOG = LoggerFactory.getLogger(Orea.class);

    /**
     * TODO: cmd line arguments to control tasks and configuration.
     *
     * @param args cmd line arguments (not used yet)
     */
    public static void main(String... args) {
        //new SimpleTask1().run();
        //new SimpleTask2().run();
        new ShuffleTask1().run();
    }

    // -----------------------------------------------------------------------------------------------------------------

    public static void printResult(Automaton target, Population<RX> result) {
        RX winner = result.best().getGenotype();
        boolean isEquivalent = AutomatonHelper.isEquivalent(target, new RegExp(winner.show()).toAutomaton());

        LOG.info("Winner = '{}'", winner.show());
        LOG.info("Is equivalent? {}", isEquivalent ? "YES" : "NO");
    }
}
