package de.jeha.oreaj.regex.rx;

import java.util.Arrays;
import java.util.List;

public abstract class Op2 extends AbstractRX {

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
    final public List<RX> siblings() {
        return Arrays.asList(getRight(), getLeft());
    }

    @Override
    public final void substitute(RX rx, RX substitute) {
        assert left.equals(rx) || right.equals(rx);
        if (left.equals(rx)) {
            left = substitute;
        } else {
            right = substitute;
        }
    }

    @Override
    public int depth() {
        return Math.max(getLeft().depth(), getRight().depth()) + 1;
    }

}
