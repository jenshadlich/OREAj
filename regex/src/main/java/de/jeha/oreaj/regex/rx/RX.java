package de.jeha.oreaj.regex.rx;

import java.util.List;

public interface RX extends Iterable<RX> {

    String show();

	List<RX> siblings();

    RX deepClone();

}
