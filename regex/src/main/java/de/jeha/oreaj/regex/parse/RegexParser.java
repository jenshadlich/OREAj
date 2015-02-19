package de.jeha.oreaj.regex.parse;

import de.jeha.oreaj.regex.rx.*;

/**
 *
 */
public class RegexParser extends Parser {

    public RegexParser(String input) {
        super(input);
    }

    private RX literal() throws NoParseException {
        String a = lookAhead();
        if (isLetter(a)) {
            consume(a);
            return new Letter(a);
        }
        throw new NoParseException("letter expected");
    }

    private RX atom() throws NoParseException {
        if (tryMatch("(")) {
            RX rx = expr();
            match(")");
            return rx;
        }
        return literal();
    }

    private RX factor() throws NoParseException {
        return stars(atom());
    }

    private RX stars(RX rx) {
        if (tryMatch("*")) {
            return stars(new Star(rx));
        }
        return rx;
    }

    private RX term() throws NoParseException {
        RX rx = factor();
        String a = lookAhead();
        if (isLetter(a) || a.equals("(")) {
            return new Dot(rx, term());
        }
        return rx;
    }

    private RX expr() throws NoParseException {
        RX rx = term();
        if (tryMatch("+")) {
            return new Union(rx, expr());
        }
        if (tryMatch(".")) {
            return new Dot(rx, expr());
        }
        if (tryMatch("$")) {
            return new Shuffle(rx, expr());
        }
        return rx;
    }

    public RX parse() throws NoParseException {
        RX rx = expr();
        if (remainder.length() > 0) {
            throw new NoParseException("Too many characters left");
        }
        errorPosition = remainder.length() - input.length();

        return rx;
    }

}
