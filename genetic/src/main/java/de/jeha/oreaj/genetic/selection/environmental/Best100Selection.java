package de.jeha.oreaj.genetic.selection.environmental;

import de.jeha.oreaj.genetic.Configuration;
import de.jeha.oreaj.genetic.Individual;
import de.jeha.oreaj.genetic.Population;
import de.jeha.oreaj.genetic.selection.EnvironmentalSelection;

import java.util.List;
import java.util.stream.Collectors;

public class Best100Selection<GT> implements EnvironmentalSelection<GT> {

    private final Configuration configuration;

    public Best100Selection(final Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public Population<GT> select(final Population<GT> population) {

        final List<Individual<GT>> populationRankedByFitness = population
                .stream()
                .sorted()
                .collect(Collectors.toList());

        return new Population<>(populationRankedByFitness
                .stream()
                .limit(configuration.getPopulationSize())
                .collect(Collectors.toList()));
    }

}
