package de.jeha.oreaj.regex.parse;

class Parser {

    protected static final String ALPHA = "abcdefghijklmnopqrstuvwxyz";
    protected String v; // zu analysierendes Eingabewort
    protected String errorMessage;
    protected int errorPosition;

    protected String lookAhead() {
        if (v.length() > 0)
            return v.substring(0, 1); // first character
        return "";
    }

    protected void consume(String a) {
        v = v.substring(1); // the rest but the first character
    }

    protected boolean tryMatch(String a) {
        if (v.startsWith(a)) {
            consume(a);
            return true;
        }
        return false;
    }

    protected void match(String a) throws NoParseException {
        if (!tryMatch(a))
            throw new NoParseException("Character '" + a + "' expected");
    }

    protected boolean isIn(String a, String set) {
        return a.length() > 0 && set.contains(a);
    }

    protected boolean isLetter(String a) {
        return isIn(a, ALPHA);
    }
}
