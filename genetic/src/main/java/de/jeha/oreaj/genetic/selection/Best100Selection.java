package de.jeha.oreaj.genetic.selection;

import de.jeha.oreaj.genetic.Configuration;
import de.jeha.oreaj.genetic.Individual;
import de.jeha.oreaj.genetic.Population;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Best100Selection<GT> implements EnvironmentalSelection<GT> {

    private final Configuration configuration;

    public Best100Selection(final Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public Population<GT> select(final Population<GT> p) {

        List<Individual<GT>> all = new ArrayList<>();
        List<Individual<GT>> selected = new ArrayList<>();

        for (Individual<GT> i : p) {
            all.add(i);
        }
        Collections.sort(all);

        int i = 0;
        for (Individual<GT> individual : all) {
            if (i < configuration.getPopulationSize()) {
                selected.add(individual);
                i++;
            } else {
                break;
            }
        }

        return new Population<>(selected);
    }

}
