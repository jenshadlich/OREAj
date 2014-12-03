package de.jeha.oreaj.regex.generator;

import de.jeha.oreaj.genetic.Generator;
import de.jeha.oreaj.regex.rx.RX;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RXGeneratorTest {

    private final Logger LOG = LoggerFactory.getLogger(RXGeneratorTest.class);

    @Test
    public void generate() {
        String[] sigma = {"a", "b"};
        Generator<RX> generator = new RXGenerator(3, sigma);
        for (int i = 10; i > 0; i--) {
            LOG.debug(generator.generate().show());
        }
    }
}
