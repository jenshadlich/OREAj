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
            RX x = expr();
            match(")");
            return x;
        }
        return literal();
    }

    private RX factor() throws NoParseException {
        return stars(atom());
    }

    private RX stars(RX x) {
        if (tryMatch("*")) {
            return stars(new Star(x));
        }
        return x;
    }

    private RX term() throws NoParseException {
        RX x = factor();
        String a = lookAhead();
        if (isLetter(a) || a.equals("(")) {
            return new Dot(x, term());
        }
        return x;
    }

    private RX expr() throws NoParseException {
        RX x = term();
        if (tryMatch("+")) {
            return new Union(x, expr());
        }
        if (tryMatch(".")) {
            return new Dot(x, expr());
        }
        return x;
    }

    public RX parse() throws NoParseException {
        RX z;
        z = expr();
        if (cursor.length() > 0) {
            throw new NoParseException("Too many characters left");
        }
        errorPosition = cursor.length() - input.length();

        return z;
    }

}
