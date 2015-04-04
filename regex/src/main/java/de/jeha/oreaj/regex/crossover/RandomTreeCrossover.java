package de.jeha.oreaj.regex.crossover;

import de.jeha.oreaj.genetic.core.Crossover;
import de.jeha.oreaj.regex.rx.Dot;
import de.jeha.oreaj.regex.rx.RX;
import de.jeha.oreaj.regex.rx.Union;
import de.jeha.oreaj.regex.subtree.Subtree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class RandomTreeCrossover implements Crossover<RX> {

    private static final Logger LOG = LoggerFactory.getLogger(RandomTreeCrossover.class);
    private static final Random GENERATOR = new Random();

    @Override
    public RX crossover(RX mom, RX dad) {
        RX subtreeOfMom = Subtree.randomSubtree(mom);
        RX subtreeOfDad = Subtree.randomSubtree(dad);

        return combineWithRandomGlue(subtreeOfMom, subtreeOfDad);
    }

    // -----------------------------------------------------------------------------------------------------------------

    private RX combineWithRandomGlue(RX mom, RX dad) {
        if (GENERATOR.nextBoolean()) {
            LOG.trace("crossover glue = DOT");
            return new Dot(mom, dad);
        } else {
            LOG.trace("crossover glue = UNION");
            return new Union(mom, dad);
        }
    }
}
