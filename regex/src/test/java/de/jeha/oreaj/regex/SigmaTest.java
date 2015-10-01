package de.jeha.oreaj.regex;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author jenshadlich@googlemail.com
 */
public class SigmaTest {

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorEmpty() {
        new Sigma();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNull() {
        new Sigma(null);
    }

    @Test
    public void testRandom() {
        assertEquals("a", new Sigma("a").random());
    }

}
