package de.jeha.oreaj.regex.rx;

import java.util.Iterator;

import de.jeha.oreaj.regex.iterator.PreorderIterator;

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

	public String show() {
		return getLeft().show() + getGlueString() + getRight().show();
	}

	@Override
	public Iterator<RX> iterator() {
		return new PreorderIterator(this);
	}
}
