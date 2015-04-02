package de.jeha.oreaj.genetic;

import de.jeha.oreaj.genetic.core.*;
import de.jeha.oreaj.genetic.selection.EnvironmentalSelection;
import de.jeha.oreaj.genetic.selection.parental.LinearRecombination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GeneticSolver<GT> {

    private static final Logger LOG = LoggerFactory.getLogger(GeneticSolver.class);

    private final Configuration configuration;
    private final Generator<GT> generator;
    private final Evaluator<GT> evaluator;
    private final Crossover<GT> crossover;
    private final EnvironmentalSelection<GT> environmentalSelection;

    private Population<GT> population = null;

    public GeneticSolver(Configuration configuration, Generator<GT> generator,
                         Evaluator<GT> evaluator, Crossover<GT> crossover,
                         EnvironmentalSelection<GT> environmentalSelection) {
        this.configuration = configuration;
        this.generator = generator;
        this.evaluator = evaluator;
        this.crossover = crossover;
        this.environmentalSelection = environmentalSelection;
    }

    /**
     * Let the genetic programming algorithm run to solve the given problem.
     *
     * @return final population after termination
     */
    public Population<GT> evolve() {
        LOG.info("initialize population");
        population = new Population<>(evaluate(initialize()));

        LOG.info("start evolution");
        for (int i = 1; checkIfTerminate(i); i++) {
            LOG.debug("generation = {}", i);

            // parental selection & variation
            List<GT> candidates = new LinearRecombination<>(crossover).select(population);

            // evaluate candidates and join with original population
            population.join(evaluate(candidates));

            // environmental selection
            // TODO: store newPopulation in a stack or something, maybe use memento pattern
            Population<GT> newPopulation = environmentalSelection.select(population);
            LOG.debug("environmental selection, size = {} -> {}", population.size(), newPopulation.size());
            population = newPopulation;

            LOG.debug("best = {}", population.best().getFitness());
        }

        LOG.info("done with evolution");
        return population;
    }

    // -----------------------------------------------------------------------------------------------------------------

    private boolean checkIfTerminate(int i) {
        return i <= configuration.getMaxRuns() && population.best().getFitness() > configuration.getThreshold();
    }

    private List<Individual<GT>> evaluate(List<GT> individuals) {
        return individuals
                .stream()
                .parallel()
                .map(genotype -> new Individual<>(genotype, evaluator.evaluate(genotype)))
                .collect(Collectors.toList());
    }

    private List<GT> initialize() {
        List<GT> individuals = new ArrayList<>();
        for (int i = 0; i < configuration.getPopulationSize(); i++) {
            individuals.add(generator.generate());
        }
        return individuals;
    }

}
