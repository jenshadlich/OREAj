package de.jeha.oreaj.genetic;

public interface Crossover<GT> {

    GT crossover(GT mom, GT dad);

}
