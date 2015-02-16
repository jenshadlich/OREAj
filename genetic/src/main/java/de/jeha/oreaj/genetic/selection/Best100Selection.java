package de.jeha.oreaj.genetic.selection;

import de.jeha.oreaj.genetic.Configuration;
import de.jeha.oreaj.genetic.Individual;
import de.jeha.oreaj.genetic.Population;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Best100Selection<GT> implements EnvironmentalSelection<GT> {

    private final Configuration configuration;

    public Best100Selection(final Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public Population<GT> select(final Population<GT> p) {

        List<Individual<GT>> all = new ArrayList<>();

        p.forEach(all::add);

        Collections.sort(all);

        return new Population<>(all.stream().limit(configuration.getPopulationSize()).collect(Collectors.toList()));
    }

}
