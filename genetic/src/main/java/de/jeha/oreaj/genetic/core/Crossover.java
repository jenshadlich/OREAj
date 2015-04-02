package de.jeha.oreaj.genetic.core;

public interface Crossover<GT> {

    GT crossover(GT mom, GT dad);

}
