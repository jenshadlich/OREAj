package de.jeha.oreaj.genetic.selection.environmental;

import de.jeha.oreaj.genetic.core.Configuration;
import de.jeha.oreaj.genetic.core.Individual;
import de.jeha.oreaj.genetic.core.Population;
import de.jeha.oreaj.genetic.selection.EnvironmentalSelection;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Best100UniqueSelection<GT> implements EnvironmentalSelection<GT> {

    private final Configuration configuration;

    public Best100UniqueSelection(final Configuration configuration) {
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
                .filter(distinctByGenotype())
                .limit(configuration.getPopulationMaxSize())
                .collect(Collectors.toList()));
    }

    private Predicate<Individual<GT>> distinctByGenotype() {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return i -> seen.putIfAbsent(i.getGenotype(), Boolean.TRUE) == null;
    }

}
