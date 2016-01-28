package de.jeha.oreaj.genetic;

import de.jeha.oreaj.genetic.core.*;
import de.jeha.oreaj.genetic.selection.EnvironmentalSelection;
import de.jeha.oreaj.genetic.selection.ParentalSelection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Genetic Programming algorithm to solve the given problem.
 *
 * @param <GT> genotype
 * @author jenshadlich@googlemail.com
 */
public class GeneticSolver<GT> {

    private static final Logger LOG = LoggerFactory.getLogger(GeneticSolver.class);
    private static final double DEFAULT_EPSILON = 0.1e-5;

    private final Configuration configuration;
    private final PopulationInitializer<GT> initializer;
    private final Evaluator<GT> evaluator;
    private final ParentalSelection<GT> parentalSelection;
    private final EnvironmentalSelection<GT> environmentalSelection;

    private Population<GT> population = null;

    /**
     * TODO: provide a builder, constructor is getting too big
     *
     * @param configuration          configuration
     * @param initializer            population initializer
     * @param evaluator              evaluator
     * @param parentalSelection      parental selection strategy
     * @param environmentalSelection environment selection strategy
     */
    public GeneticSolver(Configuration configuration, PopulationInitializer<GT> initializer,
                         Evaluator<GT> evaluator,
                         ParentalSelection<GT> parentalSelection,
                         EnvironmentalSelection<GT> environmentalSelection) {
        this.configuration = configuration;
        this.initializer = initializer;
        this.evaluator = evaluator;
        this.parentalSelection = parentalSelection;
        this.environmentalSelection = environmentalSelection;
    }

    /**
     * Evolve.
     *
     * @return final population after termination
     */
    public Population<GT> evolve() {
        LOG.info("initialize population");
        population = new Population<>(evaluate(initializer.initialize(configuration.getPopulationMaxSize())));

        LOG.info("start evolution");
        for (int i = 1; checkIfTerminate(i); i++) {
            LOG.debug("generation = {}", i);

            // parental selection & variation
            List<GT> candidates = parentalSelection.select(population);

            // evaluate candidates and join with original population
            population.join(evaluate(candidates));

            // environmental selection
            // TODO: store newPopulation in a stack or something, maybe use memento pattern
            Population<GT> newPopulation = environmentalSelection.select(population);
            LOG.debug("environmental selection, size = {} -> {}", population.size(), newPopulation.size());
            population = newPopulation;

            if (LOG.isDebugEnabled()) {
                Individual<GT> currentBest = population.best();
                LOG.debug("current best = {} -> {}", currentBest.getGenotype(), currentBest.getFitness());
            }
            if (LOG.isTraceEnabled()) {
                LOG.trace("population:");
                for (Individual<GT> individual : population) {
                    LOG.trace("{} -> {}", individual.getGenotype(), individual.getFitness());
                }
            }
        }

        LOG.info("done with evolution");
        return population;
    }

    // -----------------------------------------------------------------------------------------------------------------

    private boolean checkIfTerminate(int i) {
        return i <= configuration.getMaxRuns()
                && greaterThan(population.best().getFitness(), configuration.getThreshold(), DEFAULT_EPSILON);
    }

    private boolean greaterThan(double a, double b, double epsilon) {
        return a - b > epsilon;
    }

    private List<Individual<GT>> evaluate(List<GT> individuals) {
        return individuals
                .stream()
                .parallel()
                .map(genotype -> new Individual<>(genotype, evaluator.evaluate(genotype)))
                .collect(Collectors.toList());
    }

}
