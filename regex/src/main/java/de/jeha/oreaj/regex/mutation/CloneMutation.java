package de.jeha.oreaj.regex.mutation;

import de.jeha.oreaj.genetic.core.Mutation;
import de.jeha.oreaj.regex.rx.RX;

/**
 * @author jenshadlich@googlemail.com
 */
public class CloneMutation implements Mutation<RX> {

    @Override
    public RX mutate(RX genotype) {
        return genotype.deepClone();
    }
}
