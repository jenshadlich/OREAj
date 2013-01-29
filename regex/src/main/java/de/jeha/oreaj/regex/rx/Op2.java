package de.jeha.oreaj.regex.rx;

import de.jeha.oreaj.regex.iterator.PreorderIterator;

import java.util.Iterator;

public abstract class Op2 implements RX {

    private RX left;
    private RX right;

    protected Op2(RX left, RX right) {
        this.left = left;
        this.right = right;
    }

    public RX getLeft() {
        return left;
    }

    public RX getRight() {
        return right;
    }

    abstract protected String getGlueString();

    @Override
    public String show() {
        return getLeft().show() + getGlueString() + getRight().show();
    }

    @Override
    public Iterator<RX> iterator() {
        return new PreorderIterator(this);
    }
}
