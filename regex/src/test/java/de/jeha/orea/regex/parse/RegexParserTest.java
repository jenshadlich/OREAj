package de.jeha.orea.regex.parse;

import de.jeha.oreaj.regex.parse.NoParseException;
import de.jeha.oreaj.regex.parse.RegexParser;
import de.jeha.oreaj.regex.rx.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RegexParserTest {

    @Test
    public void testNormalize() throws NoParseException {
        RX expected = new Star(new Union(new Letter("a"), new Dot(new Letter("a"), new Letter("b"))));
        assertEquals(expected.show(), new RegexParser().parse("(a+ab)*").show());
    }

    @Test(expected = NoParseException.class)
    public void testNoParseException1() throws NoParseException {
        new RegexParser().parse("123");
    }

    @Test(expected = NoParseException.class)
    public void testNoParseException2() throws NoParseException {
        new RegexParser().parse("(a");
    }
}
