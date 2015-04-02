package de.jeha.oreaj.genetic.selection;

import de.jeha.oreaj.genetic.core.Population;

public interface EnvironmentalSelection<GT> {

    Population<GT> select(Population<GT> p);

}
