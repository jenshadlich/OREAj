package de.jeha.oreaj.regex.generator;

import de.jeha.oreaj.genetic.core.Generator;
import de.jeha.oreaj.regex.Sigma;
import de.jeha.oreaj.regex.rx.RX;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class RXGeneratorTest {

    private final Logger LOG = LoggerFactory.getLogger(RXGeneratorTest.class);

    @Test
    void generate() {
        Sigma sigma = new Sigma("a", "b");
        Generator<RX> generator = new RXGenerator(3, sigma);
        for (int i = 10; i > 0; i--) {
            LOG.debug(generator.generate().show());
        }
    }
}
