package de.jeha.oreaj.regex.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

import de.jeha.oreaj.regex.rx.Op1;
import de.jeha.oreaj.regex.rx.Op2;
import de.jeha.oreaj.regex.rx.RX;

/**
 * Iterator for preorder traversal of a RX tree.
 * 
 * @author Jens
 * 
 */
public class PreorderIterator implements Iterator<RX> {

	private final Stack<RX> path = new Stack<RX>();

	public PreorderIterator(RX tree) {
		this.path.push(tree);
	}

	@Override
	public boolean hasNext() {
		return !this.path.isEmpty();
	}

	@Override
	public RX next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		RX next = path.pop();

		// TODO: refactor to don't use instanceof
		if (next instanceof Op2) {
			Op2 o = (Op2) next;
			path.push(o.getRight());
			path.push(o.getLeft());
		} else if (next instanceof Op1) {
			Op1 o = (Op1) next;
			path.push(o.getDown());
		}
		return next;
	}

	/**
	 * Not implemented. Always throws {@link UnsupportedOperationException}
	 */
	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

}
