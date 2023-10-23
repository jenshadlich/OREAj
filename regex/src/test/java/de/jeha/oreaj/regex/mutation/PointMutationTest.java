package de.jeha.oreaj.regex.mutation;

import de.jeha.oreaj.regex.Sigma;
import de.jeha.oreaj.regex.rx.Letter;
import de.jeha.oreaj.regex.rx.RX;
import de.jeha.oreaj.regex.rx.Star;
import de.jeha.oreaj.regex.rx.Union;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointMutationTest {

    @Test
    void test() {
        Sigma sigma = new Sigma("a", "b", "c");
        RX rx = new Star(new Union(new Letter("a"), new Letter("b"))); // (a|b)*
        RX mutant = new PointMutation(sigma).mutate(rx);

        assertNotEquals(rx, mutant);
        assertTrue(mutant.show().equals("(b|b)*")
                || mutant.show().equals("(c|b)*")
                || mutant.show().equals("(a|a)*")
                || mutant.show().equals("(a|c)*"));
    }

    @Test
    void testWithRXBeingOnlyOneLetter() {
        Sigma sigma = new Sigma("a", "b");
        RX rx = new Letter("a");
        RX mutant = new PointMutation(sigma).mutate(rx);

        assertNotEquals(rx, mutant);
        assertEquals(mutant.show(), new Letter("b").show());
    }

    @Test
    void testWithStarAndLetter() {
        Sigma sigma = new Sigma("a", "b");
        RX rx = new Star(new Letter("a"));
        RX mutant = new PointMutation(sigma).mutate(rx);

        assertNotEquals(rx, mutant);
        assertEquals(mutant.show(), new Star(new Letter("b")).show());
    }

}
