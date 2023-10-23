package de.jeha.oreaj.regex.iterator;

import de.jeha.oreaj.regex.rx.RX;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * Iterator for preorder traversal of a RX tree.
 */
public class PreorderIterator implements Iterator<RX> {

    private final Stack<RX> path = new Stack<>();

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
        next.siblings().forEach(path::push);

        return next;
    }

    /**
     * Not implemented by intention. Always throw a {@link UnsupportedOperationException}.
     */
    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

}
