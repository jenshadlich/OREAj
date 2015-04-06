package de.jeha.oreaj.regex.mutation;

import de.jeha.oreaj.genetic.core.Mutation;
import de.jeha.oreaj.regex.rx.Letter;
import de.jeha.oreaj.regex.rx.RX;
import de.jeha.oreaj.regex.subtree.Subtree;

/**
 * @author jenshadlich@googlemail.com
 */
public class CollapseSubtreeToRandomTerminalMutation implements Mutation<RX> {

    private static final java.util.Random GENERATOR = new java.util.Random();

    private final String[] sigma;

    public CollapseSubtreeToRandomTerminalMutation(String[] sigma) {
        this.sigma = sigma;
    }

    @Override
    public RX mutate(RX genotype) {
        RX mutant = genotype.deepClone();

        RX subtree = Subtree.randomSubtree(mutant);
        subtree.substitute(subtree, new Letter(sigma[GENERATOR.nextInt(sigma.length)]));

        return mutant;
    }
}
