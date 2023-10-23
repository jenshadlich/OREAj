package de.jeha.oreaj.regex.mutation;

import de.jeha.oreaj.regex.Sigma;
import de.jeha.oreaj.regex.rx.Letter;
import de.jeha.oreaj.regex.rx.RX;
import de.jeha.oreaj.regex.rx.Star;
import de.jeha.oreaj.regex.rx.Union;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CollapseSubtreeToRandomTerminalMutationTest {

    @Test
    void test() {
        Sigma sigma = new Sigma("a", "b");
        RX rx = new Star(new Union(new Letter("a"), new Letter("b"))); // (a|b)*
        RX mutant = new CollapseSubtreeToRandomTerminalMutation(sigma).mutate(rx);

        assertNotEquals(rx, mutant);

        Set<String> possibleMutations = new HashSet<>(Arrays.asList(
                "(a)*", "(b)*", "(a|a)*", "(a|b)*", "(b|b)*"
        ));

        final String message = String.format("Mutant '%s' not in possible mutation set", mutant.show());
        assertTrue(possibleMutations.contains(mutant.show()), message);
    }
}
