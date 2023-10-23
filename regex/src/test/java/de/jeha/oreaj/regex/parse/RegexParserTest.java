package de.jeha.oreaj.regex.parse;

import de.jeha.oreaj.regex.rx.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RegexParserTest {

    @Test
    void test() throws NoParseException {
        RX expected = new Star(new Union(new Letter("a"), new Dot(new Letter("a"), new Letter("b"))));
        assertEquals(expected.show(), new RegexParser("(a|ab)*").parse().show());
    }

    @Test
    void testLetterUnionLetter() throws NoParseException {
        RX expected = new Union(new Letter("a"), new Letter("b"));
        assertEquals(expected.show(), new RegexParser("a|b").parse().show());
    }

    @Test
    void testParseWithShuffle() throws NoParseException {
        RX expected = new Shuffle(new Star(new Dot(new Letter("a"), new Letter("a"))), new Star(new Letter("b")));
        assertEquals(expected.show(), new RegexParser("(aa)*$b*").parse().show());
    }

    @Test
    void testParseWithShuffleAndSpace() throws NoParseException {
        RX expected = new Shuffle(new Letter("a"), new Letter("b"));
        assertEquals(expected.show(), new RegexParser("a  $  b").parse().show());
    }

    @Test
    void testNoParseException1() {
        assertThrows(NoParseException.class, () -> new RegexParser("123").parse());
    }

    @Test
    void testNoParseException2() {
        assertThrows(NoParseException.class, () -> new RegexParser("(a").parse());
    }

    @Test
    void testNoParseException3() {
        assertThrows(NoParseException.class, () -> new RegexParser("a)").parse());
    }

    @Test
    void testNoParseExceptionMessage() {
        String exceptionMessage = "";
        try {
            new RegexParser("a)").parse();
        } catch (NoParseException e) {
            exceptionMessage = e.getMessage();
        }
        assertEquals("Too many characters left (pos 2)", exceptionMessage);
    }
}
