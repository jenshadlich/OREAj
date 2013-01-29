package de.jeha.oreaj.regex.rx;

import java.util.Iterator;

import de.jeha.oreaj.regex.iterator.PreorderIterator;

public abstract class Op1 implements RX {
	private RX down;

	protected Op1(RX down) {
		this.down = down;
	}

	public RX getDown() {
		return down;
	}

	@Override
	public Iterator<RX> iterator() {
		return new PreorderIterator(this);
	}

}
