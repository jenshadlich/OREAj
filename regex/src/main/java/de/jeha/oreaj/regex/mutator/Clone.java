package de.jeha.oreaj.regex.mutator;

import de.jeha.oreaj.genetic.core.Mutator;
import de.jeha.oreaj.regex.parse.NoParseException;
import de.jeha.oreaj.regex.parse.RegexParser;
import de.jeha.oreaj.regex.rx.RX;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jenshadlich@googlemail.com
 */
public class Clone implements Mutator<RX> {

    private static final Logger LOG = LoggerFactory.getLogger(Clone.class);

    @Override
    public RX mutate(RX genotype) {
        // TODO: replace with call to deepClone() but first find out why test case fails with RXGenerator
        try {
            return new RegexParser(genotype.show()).parse();
        } catch (NoParseException e) {
            LOG.warn("Failed to clone rx", e);
            return null;
        }
    }
}
