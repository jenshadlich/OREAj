package de.jeha.oreaj.genetic.selection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.jeha.oreaj.genetic.Config;
import de.jeha.oreaj.genetic.Individuum;
import de.jeha.oreaj.genetic.Population;

public class Best100Selection<GT> implements EnvironmentalSelection<GT> {

	final Config c;

	public Best100Selection(final Config c) {
		this.c = c;
	}

	@Override
	public Population<GT> select(final Population<GT> p) {

		List<Individuum<GT>> all = new ArrayList<Individuum<GT>>();
		List<Individuum<GT>> selected = new ArrayList<Individuum<GT>>();

		for (Individuum<GT> i : p) {
			all.add(i);
		}
		Collections.sort(all);

		int i = 0;
		for (Individuum<GT> individuum : all) {
			if (i < c.getPopSize()) {
				selected.add(individuum);
				i++;
			} else {
				break;
			}
		}

		return new Population<GT>(selected);
	}

}
