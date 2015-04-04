package de.jeha.oreaj.genetic.selection.parental;

import de.jeha.oreaj.genetic.core.Crossover;
import de.jeha.oreaj.genetic.core.Individual;
import de.jeha.oreaj.genetic.core.Mutation;
import de.jeha.oreaj.genetic.core.Population;
import de.jeha.oreaj.genetic.selection.ParentalSelection;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jenshadlich@googlemail.com
 */
public class LinearVariation<GT> implements ParentalSelection<GT> {

    private static final java.util.Random GENERATOR = new java.util.Random();

    private final Crossover<GT> crossover;
    private final Mutation<GT> mutation;

    public LinearVariation(Crossover<GT> crossover, Mutation<GT> mutation) {
        this.crossover = crossover;
        this.mutation = mutation;
    }

    @Override
    public List<GT> select(Population<GT> population) {

        List<GT> candidates = new ArrayList<>();
        boolean doCrossover = GENERATOR.nextBoolean();

        GT previous = null;
        for (Individual<GT> individual : population) {
            if (doCrossover) {
                if (previous == null) {
                    previous = individual.getGenotype();
                } else {
                    GT child = crossover.crossover(previous, individual.getGenotype());
                    previous = null;
                    candidates.add(child);
                }
            } else {
                candidates.add(mutation.mutate(individual.getGenotype()));
            }
        }

        return candidates;
    }

}
