package de.jeha.oreaj.genetic.selection;

import de.jeha.oreaj.genetic.Population;

public interface EnvironmentalSelection<GT> {
	public Population<GT> select(Population<GT> p);
}
