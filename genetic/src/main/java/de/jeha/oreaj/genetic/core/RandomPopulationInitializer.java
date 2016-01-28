package de.jeha.oreaj.genetic.core;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jenshadlich@googlemail.com
 */
public class RandomPopulationInitializer<GT> implements PopulationInitializer<GT> {

    private final Generator<GT> generator;

    public RandomPopulationInitializer(Generator<GT> generator) {
        this.generator = generator;
    }

    @Override
    public List<GT> initialize(int size) {
        List<GT> individuals = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            individuals.add(generator.generate());
        }
        return individuals;
    }

}
