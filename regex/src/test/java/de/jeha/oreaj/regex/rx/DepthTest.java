package de.jeha.oreaj.regex.rx;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author jenshadlich@googlemail.com
 */
public class DepthTest {

    @Test
    public void testLetterOnly() {
        RX rx = new Letter("a");

        assertEquals(0, rx.depth());
    }

    @Test
    public void testStarAndLetter() {
        RX rx = new Star(new Letter("a"));

        assertEquals(1, rx.depth());
    }

    @Test
    public void testUnionAndLetters() {
        RX rx = new Union(new Letter("a"), new Letter("b"));

        assertEquals(1, rx.depth());
    }

    @Test
    public void test() {
        RX rx = new Star(new Dot((new Letter("a")), new Union(new Letter("b"), new Letter("c"))));

        assertEquals(3, rx.depth());
    }

}
