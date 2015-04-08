package de.jeha.oreaj.regex.mutation;

import de.jeha.oreaj.genetic.core.Mutation;
import de.jeha.oreaj.regex.rx.RX;
import de.jeha.oreaj.regex.rx.Star;
import de.jeha.oreaj.regex.subtree.Subtree;

import java.util.List;

/**
 * @author jenshadlich@googlemail.com
 */
public class StarMutation implements Mutation<RX> {

    private static final java.util.Random GENERATOR = new java.util.Random();

    @Override
    public RX mutate(RX rx) {
        final RX mutant = rx.deepClone();
        final RX subtree = Subtree.randomSubtree(mutant);
        final List<RX> siblings = subtree.siblings();

        if (siblings.size() > 0) {
            RX selectedChild = siblings.get(GENERATOR.nextInt(siblings.size()));
            subtree.substitute(selectedChild, new Star(selectedChild));
        }

        return mutant;
    }
}
