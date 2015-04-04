package de.jeha.oreaj.regex.mutation;

import de.jeha.oreaj.genetic.core.Mutation;
import de.jeha.oreaj.regex.rx.RX;

import java.util.List;

/**
 * @author jenshadlich@googlemail.com
 */
public class PointMutation implements Mutation<RX> {

    private static final java.util.Random GENERATOR = new java.util.Random();

    @Override
    public RX mutate(RX rx) {
        RX mutant = rx.deepClone();
        RX next = mutant;
        do {
            final List<RX> siblings = next.siblings();
            if (siblings.size() > 0) {
                // find a path
                next = siblings.get(GENERATOR.nextInt(siblings.size()));
            } else {
                // TODO: replace terminal symbol Letter by different instance, needs to know 'sigma'
                break;
            }
        } while(true);

        return mutant;
    }
}
