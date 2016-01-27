package de.jeha.oreaj.genetic.core;

/**
 * @author jenshadlich@googlemail.com
 */
public interface Evaluator<GT> {

    double evaluate(GT individual);

}
