package de.jeha.oreaj.regex.rx;

public class Union extends Op2 {

    private final static String GLUE_STRING = "|";

    public Union(RX left, RX right) {
        super(left, right);
    }

    @Override
    protected String getGlueString() {
        return GLUE_STRING;
    }

    @Override
    public RX deepClone() {
        return new Union(getLeft().deepClone(), getRight().deepClone());
    }
}
