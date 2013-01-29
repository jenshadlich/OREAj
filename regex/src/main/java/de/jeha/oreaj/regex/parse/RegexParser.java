package de.jeha.oreaj.regex.parse;

import de.jeha.oreaj.regex.rx.*;

/**
 *
 */
public class RegexParser extends Parser {

    private RX literal() throws NoParseException {
        String a = lookahead();
        if (isLetter(a)) {
            consume(a);
            return new Letter(a);
        }
        throw new NoParseException("Buchstabe erwartet");
    }

    private RX atom() throws NoParseException {
        if (trymatch("(")) {
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
        if (trymatch("*"))
            return stars(new Star(x));
        return x;
    }

    private RX term() throws NoParseException {
        RX x = factor();
        String a = lookahead();
        if (isLetter(a) || a.equals("("))
            return new Dot(x, term());
        return x;
    }

    private RX expr() throws NoParseException {
        RX x = term();
        if (trymatch("+"))
            return new Union(x, expr());
        if (trymatch("."))
            return new Dot(x, expr());
        return x;
    }

    public RX parse(String a) throws NoParseException {
        RX z = null;
        v = a;
        errorMessage = "OK";
        z = expr();
        if (v.length() > 0)
            throw new NoParseException("Überzählige Zeichen");

        errorPosition = a.length() - v.length();
        return z;
    }

}
