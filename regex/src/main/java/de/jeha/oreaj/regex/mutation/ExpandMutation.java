package de.jeha.oreaj.regex.mutation;

import de.jeha.oreaj.genetic.core.Mutation;
import de.jeha.oreaj.regex.Sigma;
import de.jeha.oreaj.regex.generator.RXGenerator;
import de.jeha.oreaj.regex.rx.RX;
import de.jeha.oreaj.regex.subtree.Subtree;

import java.util.List;

/**
 * @author jenshadlich@googlemail.com
 */
public class ExpandMutation implements Mutation<RX> {

    private static final java.util.Random GENERATOR = new java.util.Random();

    private final RXGenerator rxGenerator;

    public ExpandMutation(Sigma sigma) {
        rxGenerator = new RXGenerator(3, sigma);
    }

    @Override
    public RX mutate(RX rx) {
        final RX mutant = rx.deepClone();
        final RX subtree = Subtree.randomSubtree(mutant);

        // if it's not a terminal, replace a random sibling
        if (subtree.hasSiblings()) {
            final List<RX> siblings = subtree.siblings();
            final RX selectedChild = siblings.get(GENERATOR.nextInt(siblings.size()));
            subtree.substitute(selectedChild, rxGenerator.generate());
        }

        return mutant;
    }

}
