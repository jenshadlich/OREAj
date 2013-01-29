package de.jeha.oreaj.genetic.selection;

import de.jeha.oreaj.genetic.Individual;
import de.jeha.oreaj.genetic.Population;

import java.util.List;

public interface ParentalSelection<GT> {

    List<Individual<GT>> select(Population<GT> p);
}
