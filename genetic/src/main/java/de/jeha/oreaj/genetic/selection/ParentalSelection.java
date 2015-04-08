package de.jeha.oreaj.genetic.selection;

import de.jeha.oreaj.genetic.core.Population;

import java.util.List;

/**
 * TODO: Refactor; currently more used for both selection AND (parental) variation.
 *
 * @param <GT> genotype
 */
public interface ParentalSelection<GT> {

    List<GT> select(Population<GT> population);
}
