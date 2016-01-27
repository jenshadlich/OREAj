package de.jeha.oreaj.genetic.core;

import java.util.List;

/**
 * @author jenshadlich@googlemail.com
 */
public interface PopulationInitializer<GT> {

    List<GT> initialize();

}
