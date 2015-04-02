package de.jeha.oreaj.regex.mutator;

import de.jeha.oreaj.genetic.core.Mutator;
import de.jeha.oreaj.regex.rx.RX;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

/**
 * @author jenshadlich@googlemail.com
 */
public class Random implements Mutator<RX> {

    private static final Logger LOG = LoggerFactory.getLogger(Random.class);

    private static final java.util.Random GENERATOR = new java.util.Random();

    private static final List<Mutator<RX>> MUTATORS = Arrays.asList(
            new Clone()
    );

    @Override
    public RX mutate(RX genotype) {
        final Mutator<RX> mutator = MUTATORS.get(GENERATOR.nextInt(MUTATORS.size()));
        LOG.debug("Chosen mutation: {}", mutator.getClass().getName());
        return mutator.mutate(genotype);
    }
}
