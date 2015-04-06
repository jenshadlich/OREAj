package de.jeha.oreaj.regex.parse;

import de.jeha.oreaj.regex.rx.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RegexParserTest {

    @Test
    public void test() throws NoParseException {
        RX expected = new Star(new Union(new Letter("a"), new Dot(new Letter("a"), new Letter("b"))));
        assertEquals(expected.show(), new RegexParser("(a+ab)*").parse().show());
    }

    @Test
    public void testLetterUnionLetter() throws NoParseException {
        RX expected = new Union(new Letter("a"), new Letter("b"));
        assertEquals(expected.show(), new RegexParser("a|b").parse().show());
    }

    @Test
    public void testParseWithShuffle() throws NoParseException {
        RX expected = new Shuffle(new Star(new Dot(new Letter("a"), new Letter("a"))), new Star(new Letter("b")));
        assertEquals(expected.show(), new RegexParser("(aa)*$b*").parse().show());
    }

    @Test
    public void testParseWithShuffleAndSpace() throws NoParseException {
        RX expected = new Shuffle(new Letter("a"), new Letter("b"));
        assertEquals(expected.show(), new RegexParser("a  $  b").parse().show());
    }

    @Test(expected = NoParseException.class)
    public void testNoParseException1() throws NoParseException {
        new RegexParser("123").parse();
    }

    @Test(expected = NoParseException.class)
    public void testNoParseException2() throws NoParseException {
        new RegexParser("(a").parse();
    }

    @Test(expected = NoParseException.class)
    public void testNoParseException3() throws NoParseException {
        new RegexParser("a)").parse();
    }

    @Test
    public void testNoParseExceptionMessage() {
        String exceptionMessage = "";
        try {
            new RegexParser("a)").parse();
        } catch (NoParseException e) {
            exceptionMessage = e.getMessage();
        }
        assertEquals("Too many characters left (pos 2)", exceptionMessage);
    }
}
