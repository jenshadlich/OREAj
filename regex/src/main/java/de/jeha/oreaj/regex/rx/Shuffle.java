package de.jeha.oreaj.regex.rx;

public class Shuffle extends Op2 {

    private final static String GLUE_STRING = "$";

    public Shuffle(RX left, RX right) {
        super(left, right);
    }

    @Override
    protected String getGlueString() {
        return GLUE_STRING;
    }

    @Override
    public RX deepClone() {
        return new Shuffle(getLeft(), getRight());
    }
}
