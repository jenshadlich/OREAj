package de.jeha.oreaj.regex.rx;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * @author jenshadlich@googlemail.com
 */
class DeepCloneTest {

    @Test
    void test() {
        Dot rx = new Dot(new Star(new Letter("a")), new Letter("b"));
        RX mutant = rx.deepClone();

        assertEquals(rx.show(), mutant.show());
        assertNotEquals(rx, mutant);
        assertNotEquals(rx.getLeft(), ((Dot) mutant).getLeft());
        assertNotEquals(rx.getRight(), ((Dot) mutant).getRight());
    }
}
