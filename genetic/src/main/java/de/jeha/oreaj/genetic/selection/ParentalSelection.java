package de.jeha.oreaj.genetic.selection;

import java.util.List;

import de.jeha.oreaj.genetic.Individuum;
import de.jeha.oreaj.genetic.Population;

public interface ParentalSelection<GT> {

	public List<Individuum<GT>> select(Population<GT> p);
}
