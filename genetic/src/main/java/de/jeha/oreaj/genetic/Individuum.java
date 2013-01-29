package de.jeha.oreaj.genetic;

public class Individuum<GT> implements Comparable<Individuum<GT>> {

	private GT genotype = null;
	private double fitness;
	
	public Individuum(GT genotype, double fitness) {
		this.genotype = genotype;
		this.fitness = fitness;
	}

	public GT getGenotype() {
		return genotype;
	}

	public double getFitness() {
		return fitness;
	}

	@Override
	public int compareTo(Individuum<GT> o) {
		return Double.compare(this.getFitness(), o.getFitness());
	}

}
