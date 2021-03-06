package de.jeha.oreaj.regex.rx;

import java.util.Collections;
import java.util.List;

public abstract class Op1 extends AbstractRX {

    private RX down;

    protected Op1(RX down) {
        this.down = down;
    }

    public RX getDown() {
        return down;
    }

    @Override
    final public List<RX> siblings() {
        return Collections.singletonList(getDown());
    }

    @Override
    public boolean hasSiblings() {
        return true;
    }

    @Override
    public final void substitute(RX rx, RX substitute) {
        assert down.equals(rx);
        down = substitute;
    }

    @Override
    public int depth() {
        return getDown().depth() + 1;
    }

}
