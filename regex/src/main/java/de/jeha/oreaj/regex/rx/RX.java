package de.jeha.oreaj.regex.rx;

import java.util.List;

/**
 * @author jenshadlich@googlemail.com
 */
public interface RX extends Iterable<RX> {

    /**
     * @return a string representation of the rx
     */
    String show();

    /**
     * @return a list of all siblings (2 for Op2, 1 for Op1 or empty for terminal symbol Letter)
     */
    List<RX> siblings();

    /**
     * Make a deep copy of the current object.
     *
     * @return the identical clone
     */
    RX deepClone();

    /**
     * TODO: Not so nice, find alternative solution.
     *
     * @param rx instance of the sibling to replace (needed e.g. for Op2 which has 2 siblings)
     * @param substitute new sibling
     */
    void substitute(RX rx, RX substitute);
}
