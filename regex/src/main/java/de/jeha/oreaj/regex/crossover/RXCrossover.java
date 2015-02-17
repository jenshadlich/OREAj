package de.jeha.oreaj.regex.crossover;

import de.jeha.oreaj.genetic.Crossover;
import de.jeha.oreaj.regex.rx.Dot;
import de.jeha.oreaj.regex.rx.RX;
import de.jeha.oreaj.regex.rx.Union;
import de.jeha.oreaj.regex.subtree.Subtree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class RXCrossover implements Crossover<RX> {

    private static final Logger LOG = LoggerFactory.getLogger(RXCrossover.class);
    private static final Random GENERATOR = new Random();

    @Override
    public RX crossover(RX mom, RX dad) {
        RX stMom = Subtree.randomSubtree(mom);
        RX stDad = Subtree.randomSubtree(dad);

        return combineWithRandomGlue(stMom, stDad);
    }

    // -----------------------------------------------------------------------------------------------------------------

    private RX combineWithRandomGlue(RX mom, RX dad) {
        if (GENERATOR.nextBoolean()) {
            LOG.debug("crossover glue = DOT");
            return new Dot(mom, dad);
        } else {
            LOG.debug("crossover glue = UNION");
            return new Union(mom, dad);
        }
    }
}
