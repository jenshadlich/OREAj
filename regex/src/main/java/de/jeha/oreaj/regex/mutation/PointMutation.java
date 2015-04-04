package de.jeha.oreaj.regex.mutation;

import de.jeha.oreaj.genetic.core.Mutation;
import de.jeha.oreaj.regex.rx.Letter;
import de.jeha.oreaj.regex.rx.RX;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author jenshadlich@googlemail.com
 */
public class PointMutation implements Mutation<RX> {

    private static final java.util.Random GENERATOR = new java.util.Random();

    private final String[] sigma;

    public PointMutation(String[] sigma) {
        this.sigma = sigma;
    }

    @Override
    public RX mutate(RX rx) {
        RX mutant = rx.deepClone();
        RX parent = mutant;
        RX child = mutant;
        do {
            final List<RX> siblings = child.siblings();
            if (siblings.size() > 0) {
                // find a path
                parent = child;
                child = siblings.get(GENERATOR.nextInt(siblings.size()));
            } else {
                // the 'child' must be a Letter
                // enforce to not replace a Letter with a equal Letter
                Set<String> sigmaSetForSubstitution = new HashSet<>(Arrays.asList(sigma));
                if (sigma.length > 1) {
                    final String terminalToExclude = child.show();
                    sigmaSetForSubstitution.remove(terminalToExclude);
                }
                String[] sigmaForSubstitution = sigmaSetForSubstitution.toArray(new String[0]);
                Letter letter = new Letter(sigmaForSubstitution[GENERATOR.nextInt(sigmaForSubstitution.length)]);
                if (parent.equals(child) && parent.equals(mutant)) {
                    mutant = letter;
                } else {
                    parent.substitute(child, letter);
                }
                break;
            }
        } while (true);

        return mutant;
    }
}
