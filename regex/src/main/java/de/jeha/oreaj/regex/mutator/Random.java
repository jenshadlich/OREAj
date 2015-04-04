package de.jeha.oreaj.regex.mutator;

import de.jeha.oreaj.genetic.core.Mutation;
import de.jeha.oreaj.regex.rx.RX;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

/**
 * @author jenshadlich@googlemail.com
 */
public class Random implements Mutation<RX> {

    private static final Logger LOG = LoggerFactory.getLogger(Random.class);

    private static final java.util.Random GENERATOR = new java.util.Random();

    private static final List<Mutation<RX>> MUTATIONS = Arrays.asList(
            new CloneMutation()
    );

    @Override
    public RX mutate(RX genotype) {
        final Mutation<RX> mutation = MUTATIONS.get(GENERATOR.nextInt(MUTATIONS.size()));
        LOG.debug("Chosen mutation: {}", mutation.getClass().getName());
        return mutation.mutate(genotype);
    }
}
