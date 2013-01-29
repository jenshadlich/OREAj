package de.jeha.oreaj.genetic;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.jeha.oreaj.genetic.selection.Best100Selection;

public class Genetic<GT> {
	private final Config c;
	private Population<GT> p = null;
	private final Generator<GT> g;
	private final Evaluator<GT> e;
	private final Crossover<GT> x;
	private final Logger LOG = LoggerFactory.getLogger(Genetic.class);

	public Genetic(final Config c, final Generator<GT> g,
			final Evaluator<GT> e, final Crossover<GT> x) {
		this.c = c;
		this.g = g;
		this.e = e;
		this.x = x;
	}

	public Population<GT> evolve() {
		LOG.debug("evolve()");
		p = new Population<GT>(evaluate(initialize()));

		for (int i = 1; i <= c.maxRuns && p.best().getFitness() > c.threshold; i++) {
			LOG.debug("generation = {}", i);
			step();
			LOG.debug("best = {}", p.best().getFitness());
		}

		return p;
	}

	// -------------------------------------------------------------------------
	// private methods
	// -------------------------------------------------------------------------

	private List<Individuum<GT>> evaluate(List<GT> as) {
		List<Individuum<GT>> is = new ArrayList<Individuum<GT>>();
		for (GT g : as) {
			double fitness = this.e.evaluate(g);
			is.add(new Individuum<GT>(g, fitness));
		}
		return is;
	}

	private List<GT> initialize() {
		List<GT> is = new ArrayList<GT>();
		for (int i = 0; i < c.popSize; i++) {
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

		// environemental selection
		// TODO: store p' in a stack or something, maybe use memento pattern
		this.p = new Best100Selection<GT>(this.c).select(this.p);
	}

	private List<GT> variate() {

		List<GT> candidates = new ArrayList<GT>();

		// TODO: just xover

		GT prev = null;
		for (Individuum<GT> indiv : this.p) {
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
