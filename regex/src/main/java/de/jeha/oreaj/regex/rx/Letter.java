package de.jeha.oreaj.regex.rx;

import de.jeha.oreaj.regex.iterator.PreorderIterator;

import java.util.Iterator;

public class Letter implements RX {

    private String value;

    public Letter(String value) {
        this.value = value;
    }

    @Override
    public String show() {
        return this.value;
    }

    @Override
    public Iterator<RX> iterator() {
        return new PreorderIterator(this);
    }

}
