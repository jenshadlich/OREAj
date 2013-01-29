package de.jeha.oreaj.genetic;

import de.jeha.oreaj.genetic.selection.Best100Selection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Genetic<GT> {

    private static final Logger LOG = LoggerFactory.getLogger(Genetic.class);

    private final Configuration c;
    private Population<GT> p = null;
    private final Generator<GT> g;
    private final Evaluator<GT> e;
    private final Crossover<GT> x;

    public Genetic(final Configuration c, final Generator<GT> g,
                   final Evaluator<GT> e, final Crossover<GT> x) {
        this.c = c;
        this.g = g;
        this.e = e;
        this.x = x;
    }

    public Population<GT> evolve() {
        LOG.debug("evolve()");
        p = new Population<GT>(evaluate(initialize()));

        for (int i = 1; i <= c.getMaxRuns() && p.best().getFitness() > c.getThreshold(); i++) {
            LOG.debug("generation = {}", i);
            step();
            LOG.debug("best = {}", p.best().getFitness());
        }

        return p;
    }

    // -------------------------------------------------------------------------
    // private methods
    // -------------------------------------------------------------------------

    private List<Individual<GT>> evaluate(List<GT> as) {
        List<Individual<GT>> is = new ArrayList<Individual<GT>>();
        for (GT g : as) {
            double fitness = this.e.evaluate(g);
            is.add(new Individual<GT>(g, fitness));
        }
        return is;
    }

    private List<GT> initialize() {
        List<GT> is = new ArrayList<GT>();
        for (int i = 0; i < c.getPopulationSize(); i++) {
            GT indiv = this.g.generate();
            is.add(indiv);
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

        List<GT> candidates = new ArrayList<GT>();

        // TODO: just xover

        GT prev = null;
        for (Individual<GT> indiv : this.p) {
            if (prev == null) {
                prev = indiv.getGenotype();
                continue;
            } else {
                GT child = x.crossover(prev, indiv.getGenotype());
                prev = null;
                candidates.add(child);
            }
        }

        return candidates;
    }

}
