package de.jeha.oreaj.regex.rx;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author jenshadlich@googlemail.com
 */
class DepthTest {

    @Test
    void testLetterOnly() {
        RX rx = new Letter("a");

        assertEquals(0, rx.depth());
    }

    @Test
    void testStarAndLetter() {
        RX rx = new Star(new Letter("a"));

        assertEquals(1, rx.depth());
    }

    @Test
    void testUnionAndLetters() {
        RX rx = new Union(new Letter("a"), new Letter("b"));

        assertEquals(1, rx.depth());
    }

    @Test
    void test() {
        RX rx = new Star(new Dot((new Letter("a")), new Union(new Letter("b"), new Letter("c"))));

        assertEquals(3, rx.depth());
    }

}
