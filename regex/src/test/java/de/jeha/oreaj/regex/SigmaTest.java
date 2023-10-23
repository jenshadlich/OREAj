package de.jeha.oreaj.regex;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author jenshadlich@googlemail.com
 */
class SigmaTest {

    @Test
    void testConstructorEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Sigma());
    }

    @Test
    void testConstructorNull() {
        assertThrows(IllegalArgumentException.class, () -> new Sigma(null));
    }

    @Test
    void testRandom() {
        assertEquals("a", new Sigma("a").random());
    }

}
