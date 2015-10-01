package de.jeha.oreaj.regex.mutation;

import de.jeha.oreaj.regex.Sigma;
import de.jeha.oreaj.regex.generator.RXGenerator;
import de.jeha.oreaj.regex.rx.RX;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CloneMutationTest {

    @Test
    public void test() {
        Sigma sigma = new Sigma("a", "b", "c");
        RX rx = new RXGenerator(3, sigma).generate();
        RX mutant = new CloneMutation().mutate(rx);

        assertEquals(rx.show(), mutant.show());
        assertNotEquals(rx, mutant);
    }
}
