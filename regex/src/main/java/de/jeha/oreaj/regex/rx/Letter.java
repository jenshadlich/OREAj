package de.jeha.oreaj.regex.rx;

import java.util.Collections;
import java.util.List;

public class Letter extends AbstractRX {

    private final String value;

    public Letter(String value) {
        this.value = value;
    }

    @Override
    public String show() {
        return this.value;
    }

    @Override
    public List<RX> siblings() {
        return Collections.emptyList();
    }

    @Override
    public boolean hasSiblings() {
        return false;
    }

    @Override
    public RX deepClone() {
        return new Letter(value);
    }

    @Override
    public int depth() {
        return 0;
    }

}
