package de.jeha.oreaj.regex.parse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class Parser {

    private final Logger LOG = LoggerFactory.getLogger(Parser.class);

    protected static final String ALPHA = "abcdefghijklmnopqrstuvwxyz";
    protected String input;
    protected String cursor;
    protected int errorPosition;

    protected Parser(String input) {
        this.input = input;
        this.cursor = input;
    }

    protected String lookAhead() {
        if (cursor.length() > 0) {
            return cursor.substring(0, 1); // first character
        }
        return "";
    }

    /**
     * @param character string to consume (only needed for logging)
     */
    protected void consume(String character) {
        LOG.debug("consume string {}", character);
        cursor = cursor.substring(1); // the rest but the first character
    }

    protected boolean tryMatch(String character) {
        LOG.debug("try match {} with {}", cursor, character);
        if (cursor.startsWith(character)) {
            consume(character);
            return true;
        }
        return false;
    }

    protected void match(String a) throws NoParseException {
        if (!tryMatch(a)) {
            throw new NoParseException("Character '" + a + "' expected");
        }
    }

    protected boolean isIn(String a, String set) {
        return a.length() > 0 && set.contains(a);
    }

    protected boolean isLetter(String a) {
        return isIn(a, ALPHA);
    }
}
