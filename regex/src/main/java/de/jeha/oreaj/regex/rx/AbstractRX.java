package de.jeha.oreaj.regex.rx;

import de.jeha.oreaj.regex.iterator.PreorderIterator;

import java.util.Iterator;

public abstract class AbstractRX implements RX {

    @Override
    public final Iterator<RX> iterator() {
        return new PreorderIterator(this);
    }

    @Override
    public void substitute(RX rx, RX substitute) {
        // do nothing by default
    }
}
