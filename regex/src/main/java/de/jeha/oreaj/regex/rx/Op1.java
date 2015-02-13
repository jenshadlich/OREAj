package de.jeha.oreaj.regex.rx;

import java.util.Arrays;
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
        return Arrays.asList(getDown());
    }
}
