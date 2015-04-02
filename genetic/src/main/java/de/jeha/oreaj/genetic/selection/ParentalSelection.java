package de.jeha.oreaj.genetic.selection;

import de.jeha.oreaj.genetic.core.Individual;
import de.jeha.oreaj.genetic.core.Population;

import java.util.List;

public interface ParentalSelection<GT> {

    List<Individual<GT>> select(Population<GT> p);
}
