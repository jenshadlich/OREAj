package de.jeha.oreaj.regex.mutation;

import de.jeha.oreaj.genetic.core.Mutation;
import de.jeha.oreaj.regex.rx.Letter;
import de.jeha.oreaj.regex.rx.RX;
import de.jeha.oreaj.regex.rx.Star;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author jenshadlich@googlemail.com
 */
class RandomMutationTest {

    private static final int CALLS = 10;

    private int randomMutation1Called = 0;
    private int randomMutation2Called = 0;

    @Test
    void test() {

        RX rx = new Star(new Letter("a"));
        @SuppressWarnings("unchecked")
        Mutation<RX> randomMutation = new RandomMutation(
                genotype -> {
                    randomMutation1Called++;
                    return null;
                },
                genotype -> {
                    randomMutation2Called++;
                    return null;
                });

        for (int i = 0; i < CALLS; i++) {
            randomMutation.mutate(rx);
        }

        assertTrue(randomMutation1Called > 0);
        assertTrue(randomMutation2Called > 0);
        assertEquals(CALLS, randomMutation1Called + randomMutation2Called);
    }
}
