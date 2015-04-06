package de.jeha.oreaj.regex.mutation;

import de.jeha.oreaj.genetic.core.Mutation;
import de.jeha.oreaj.regex.rx.RX;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jenshadlich@googlemail.com
 */
public class RandomMutation implements Mutation<RX> {

    private static final Logger LOG = LoggerFactory.getLogger(RandomMutation.class);

    private static final java.util.Random GENERATOR = new java.util.Random();

    private final List<Mutation<RX>> mutations = new ArrayList<>();

    public RandomMutation(Mutation<RX> firstMutation, Mutation<RX>... additionalMutations) {
        if (firstMutation == null) {
            throw new IllegalArgumentException("parameter 'firstMutation' must not be null");
        }
        this.mutations.add(firstMutation);
        if (additionalMutations != null) {
            for (Mutation<RX> mutation : additionalMutations) {
                if (mutation != null) {
                    this.mutations.add(mutation);
                }
            }
        }
    }

    @Override
    public RX mutate(RX genotype) {
        final Mutation<RX> mutation = mutations.get(GENERATOR.nextInt(mutations.size()));
        LOG.debug("Chosen mutation: {}", mutation.getClass().getName());
        return mutation.mutate(genotype);
    }
}
