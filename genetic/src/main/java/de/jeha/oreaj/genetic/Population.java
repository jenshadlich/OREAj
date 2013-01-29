package de.jeha.oreaj.genetic;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Population<GT> implements Iterable<Individuum<GT>> {
	private List<Individuum<GT>> is = null;

	public Population(List<Individuum<GT>> is) {
		super();
		this.is = is;
	}

	public Individuum<GT> best() {
		Collections.sort(is);
		try {
			return is.get(0);
		} catch (IndexOutOfBoundsException ioobe) {
			// TODO: error handling
			return null;
		}
	}

	public void join(List<Individuum<GT>> is) {
		this.is.addAll(is);
	}

	@Override
	public Iterator<Individuum<GT>> iterator() {

		final Iterator<Individuum<GT>> it = this.is.iterator();

		return new Iterator<Individuum<GT>>() {

			@Override
			public boolean hasNext() {
				return it.hasNext();
			}

			@Override
			public Individuum<GT> next() {
				return it.next();
			}

			@Override
			public void remove() {
				it.remove();
			}
		};
	}

}
