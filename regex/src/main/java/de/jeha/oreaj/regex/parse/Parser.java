package de.jeha.oreaj.regex.parse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jenshadlich@googlemail.com
 */
class Parser {

    private final Logger LOG = LoggerFactory.getLogger(Parser.class);

    private final String input;
    private String remainder;

    protected Parser(final String input) {
        this.input = input;
        this.remainder = input;
    }

    protected String lookAhead() {
        if (remainder.length() > 0) {
            return remainder.substring(0, 1); // first character
        }
        return "";
    }

    /**
     * @param character string to consume (actually, only needed for logging)
     */
    protected void consume(String character) {
        LOG.debug("consume string '{}'", character);
        remainder = remainder.substring(1); // the rest but the first character
    }

    protected boolean tryMatch(String character) {
        LOG.debug("try match '{}' with '{}'", remainder, character);
        while (remainder.startsWith(" ")) {
            consume(" ");
        }
        if (remainder.startsWith(character)) {
            consume(character);
            return true;
        }
        return false;
    }

    protected void match(String character) throws NoParseException {
        if (!tryMatch(character)) {
            throw new NoParseException("Character '" + character + "' expected");
        }
    }

    protected boolean isLetter(String character) {
        return StringUtils.isAlpha(character);
    }

    protected boolean hasRemainingCharacters() {
        return remainder.length() > 0;
    }

    protected int getPosition() {
        return input.length() - remainder.length();
    }

}
