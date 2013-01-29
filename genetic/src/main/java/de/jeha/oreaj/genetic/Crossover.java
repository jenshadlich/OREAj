package de.jeha.oreaj.genetic;

public interface Crossover<GT> {

	public GT crossover(GT mom, GT dad);

}
