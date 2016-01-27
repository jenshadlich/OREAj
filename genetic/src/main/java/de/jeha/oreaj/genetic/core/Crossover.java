package de.jeha.oreaj.genetic.core;

/**
 * @author jenshadlich@googlemail.com
 */
public interface Crossover<GT> {

    GT crossover(GT mom, GT dad);

}
