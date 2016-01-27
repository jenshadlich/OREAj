package de.jeha.oreaj.genetic.core;

/**
 * @author jenshadlich@googlemail.com
 */
public interface Mutation<GT> {

    GT mutate(GT genotype);

}
