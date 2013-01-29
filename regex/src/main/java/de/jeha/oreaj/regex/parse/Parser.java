package de.jeha.oreaj.regex.parse;

class Parser {
    protected static final String ALPHA = "abcdefghijklmnopqrstuvwxyz";
    protected String v; // zu analysierendes Eingabewort
    protected String errorMessage;
    protected int errorPosition;

    protected String lookahead() {
        if (v.length() > 0)
            return v.substring(0, 1); // erstes Zeichen
        return "";
    }

    protected void consume(String a) {
        v = v.substring(1); // Rest außer dem ersten Zeichen
    }

    protected boolean trymatch(String a) {
        if (v.startsWith(a)) {
            consume(a);
            return true;
        }
        return false;
    }

    protected void match(String a) throws NoParseException {
        if (!trymatch(a))
            throw new NoParseException("Zeichen " + a + " erwartet");
    }

    protected boolean isIn(String a, String set) {
        if (a.length() > 0)
            return set.indexOf(a) >= 0;
        return false;
    }

    protected boolean isLetter(String a) {
        return isIn(a, ALPHA);
    }
}
