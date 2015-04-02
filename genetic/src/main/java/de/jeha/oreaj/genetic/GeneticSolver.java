package de.jeha.oreaj.genetic;

import de.jeha.oreaj.genetic.selection.environmental.Best100Selection;
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

    private Population<GT> population = null;

    public GeneticSolver(Configuration configuration, Generator<GT> generator,
                         Evaluator<GT> evaluator, Crossover<GT> crossover) {
        this.configuration = configuration;
        this.generator = generator;
        this.evaluator = evaluator;
        this.crossover = crossover;
    }

    public Population<GT> evolve() {
        LOG.debug("evolve()");
        population = new Population<>(evaluate(initialize()));

        for (int i = 1; terminate(i); i++) {
            LOG.debug("generation = {}", i);
            step();
            LOG.debug("best = {}", population.best().getFitness());
        }

        return population;
    }

    // -----------------------------------------------------------------------------------------------------------------

    private boolean terminate(int i) {
        return i <= configuration.getMaxRuns() && population.best().getFitness() > configuration.getThreshold();
    }

    private List<Individual<GT>> evaluate(List<GT> individuals) {
        return individuals
                .stream()
                .parallel()
                .map(x -> new Individual<>(x, evaluator.evaluate(x)))
                .collect(Collectors.toList());
    }

    private List<GT> initialize() {
        List<GT> individuals = new ArrayList<>();
        for (int i = 0; i < configuration.getPopulationSize(); i++) {
            individuals.add(generator.generate());
        }
        return individuals;
    }

    private void step() {
        LOG.debug("step()");

        // TODO

        // parental selection & variation
        List<GT> candidates = variate();

        // evaluate
        population.join(evaluate(candidates));

        // environmental selection
        // TODO: store p' in a stack or something, maybe use memento pattern
        // TODO: inject different selections
        population = new Best100Selection<GT>(configuration)
                .select(population);
    }

    private List<GT> variate() {

        List<GT> candidates = new ArrayList<>();

        // TODO: just xover

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
