package de.jeha.oreaj.main;

import de.jeha.oreaj.genetic.core.Population;
import de.jeha.oreaj.regex.automaton.AutomatonHelper;
import de.jeha.oreaj.regex.rx.RX;
import dk.brics.automaton.Automaton;
import dk.brics.automaton.RegExp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jenshadlich@googlemail.com
 */
public abstract class AbstractTask implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractTask.class);

    protected void printResult(Automaton target, Population<RX> result) {
        RX winner = result.best().getGenotype();
        boolean isEquivalent = AutomatonHelper.isEquivalent(target, new RegExp(winner.show()).toAutomaton());

        LOG.info("Winner = '{}'", winner.show());
        LOG.info("Is equivalent? {}", isEquivalent ? "YES" : "NO");
    }

}
