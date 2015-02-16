package de.jeha.oreaj.genetic;

public class Individual<GT> implements Comparable<Individual<GT>> {

    private final GT genotype;
    private final double fitness;

    public Individual(GT genotype, double fitness) {
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
    public int compareTo(Individual<GT> o) {
        return Double.compare(this.getFitness(), o.getFitness());
    }

}
