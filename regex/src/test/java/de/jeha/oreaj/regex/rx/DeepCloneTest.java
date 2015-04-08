package de.jeha.oreaj.regex.rx;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * @author jenshadlich@googlemail.com
 */
public class DeepCloneTest {

    @Test
    public void test() {
        RX rx = new Dot(new Star(new Letter("a")), new Letter("b"));
        RX mutant = rx.deepClone();

        assertEquals(rx.show(), mutant.show());
        assertNotEquals(rx, mutant);
        assertNotEquals(Dot.class.cast(rx).getLeft(), Dot.class.cast(mutant).getLeft());
        assertNotEquals(Dot.class.cast(rx).getRight(), Dot.class.cast(mutant).getRight());
    }
}
