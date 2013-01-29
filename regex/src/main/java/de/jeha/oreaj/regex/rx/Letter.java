package de.jeha.oreaj.regex.rx;

import java.util.Iterator;

import de.jeha.oreaj.regex.iterator.PreorderIterator;

public class Letter implements RX {
	private String value;

	public Letter(String value) {
		this.value = value;
	}

	public String show() {
		return this.value;
	}

	@Override
	public Iterator<RX> iterator() {
		return new PreorderIterator(this);
	}

}
