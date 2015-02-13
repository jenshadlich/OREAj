package de.jeha.oreaj.regex.rx;

public class Dot extends Op2 {

    private final static String GLUE_STRING_DOT = "";

    public Dot(RX left, RX right) {
        super(left, right);
    }

    @Override
    protected String getGlueString() {
        return GLUE_STRING_DOT;
    }

}
