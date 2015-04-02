package de.jeha.oreaj.genetic.selection.parental;

import de.jeha.oreaj.genetic.core.Crossover;
import de.jeha.oreaj.genetic.core.Individual;
import de.jeha.oreaj.genetic.core.Population;
import de.jeha.oreaj.genetic.selection.ParentalSelection;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jenshadlich@googlemail.com
 */
public class LinearRecombination<GT> implements ParentalSelection<GT> {

    private final Crossover<GT> crossover;

    public LinearRecombination(Crossover<GT> crossover) {
        this.crossover = crossover;
    }

    @Override
    public List<GT> select(Population<GT> population) {

        List<GT> candidates = new ArrayList<>();

        GT previous = null;
        for (Individual<GT> individual : population) {
            if (previous == null) {
                previous = individual.getGenotype();
            } else {
                GT child = crossover.crossover(previous, individual.getGenotype());
                previous = null;
                candidates.add(child);
            }
        }

        return candidates;
    }

}
