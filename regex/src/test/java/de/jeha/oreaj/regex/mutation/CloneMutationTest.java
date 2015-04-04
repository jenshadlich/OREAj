package de.jeha.oreaj.regex.mutation;

import de.jeha.oreaj.regex.rx.Letter;
import de.jeha.oreaj.regex.rx.RX;
import de.jeha.oreaj.regex.rx.Star;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CloneMutationTest {

    @Test
    public void testClone() {
        String[] sigma = {"a", "b"};
        //RX rx = new RXGenerator(3, sigma).generate(); // TODO: check, produces errors
        RX rx = new Star(new Letter("a"));
        RX mutant = new CloneMutation().mutate(rx);

        assertEquals(rx.show(), mutant.show());
        assertNotEquals(rx, mutant);
    }
}
