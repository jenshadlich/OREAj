package de.jeha.oreaj.regex.parse;

import de.jeha.oreaj.regex.rx.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RegexParserTest {

    @Test
    public void testNormalize() throws NoParseException {
        RX expected = new Star(new Union(new Letter("a"), new Dot(new Letter("a"), new Letter("b"))));
        assertEquals(expected.show(), new RegexParser("(a+ab)*").parse().show());
    }

    @Test(expected = NoParseException.class)
    public void testNoParseException1() throws NoParseException {
        new RegexParser("123").parse();
    }

    @Test(expected = NoParseException.class)
    public void testNoParseException2() throws NoParseException {
        new RegexParser("(a").parse();
    }
}
