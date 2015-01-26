package de.jeha.oreaj.genetic;

import de.jeha.oreaj.genetic.selection.Best100Selection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class GeneticSolver<GT> {

    private static final Logger LOG = LoggerFactory.getLogger(GeneticSolver.class);

    private final Configuration c;
    private Population<GT> p = null;
    private final Generator<GT> g;
    private final Evaluator<GT> e;
    private final Crossover<GT> x;

    public GeneticSolver(final Configuration c, final Generator<GT> g,
                         final Evaluator<GT> e, final Crossover<GT> x) {
        this.c = c;
        this.g = g;
        this.e = e;
        this.x = x;
    }

    public Population<GT> evolve() {
        LOG.debug("evolve()");
        p = new Population<>(evaluate(initialize()));

        for (int i = 1; terminate(i); i++) {
            LOG.debug("generation = {}", i);
            step();
            LOG.debug("best = {}", p.best().getFitness());
        }

        return p;
    }

    // -----------------------------------------------------------------------------------------------------------------

    private boolean terminate(int i) {
        return i <= c.getMaxRuns() && p.best().getFitness() > c.getThreshold();
    }

    private List<Individual<GT>> evaluate(List<GT> as) {
        List<Individual<GT>> is = new ArrayList<>();
        for (GT g : as) {
            double fitness = this.e.evaluate(g);
            is.add(new Individual<>(g, fitness));
        }
        return is;
    }

    private List<GT> initialize() {
        List<GT> is = new ArrayList<>();
        for (int i = 0; i < c.getPopulationSize(); i++) {
            GT individual = this.g.generate();
            is.add(individual);
        }
        return is;
    }

    private void step() {
        LOG.debug("step()");

        // TODO

        // parental selection & variation
        List<GT> candidates = variate();

        // evaluate
        this.p.join(evaluate(candidates));

        // environmental selection
        // TODO: store p' in a stack or something, maybe use memento pattern
        this.p = new Best100Selection<GT>(this.c).select(this.p);
    }

    private List<GT> variate() {

        List<GT> candidates = new ArrayList<>();

        // TODO: just xover

        GT prev = null;
        for (Individual<GT> individual : this.p) {
            if (prev == null) {
                prev = individual.getGenotype();
            } else {
                GT child = x.crossover(prev, individual.getGenotype());
                prev = null;
                candidates.add(child);
            }
        }

        return candidates;
    }

}
