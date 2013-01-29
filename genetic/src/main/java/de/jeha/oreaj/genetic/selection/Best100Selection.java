package de.jeha.oreaj.genetic.selection;

import de.jeha.oreaj.genetic.Configuration;
import de.jeha.oreaj.genetic.Individual;
import de.jeha.oreaj.genetic.Population;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Best100Selection<GT> implements EnvironmentalSelection<GT> {

    final Configuration c;

    public Best100Selection(final Configuration c) {
        this.c = c;
    }

    @Override
    public Population<GT> select(final Population<GT> p) {

        List<Individual<GT>> all = new ArrayList<Individual<GT>>();
        List<Individual<GT>> selected = new ArrayList<Individual<GT>>();

        for (Individual<GT> i : p) {
            all.add(i);
        }
        Collections.sort(all);

        int i = 0;
        for (Individual<GT> individual : all) {
            if (i < c.getPopSize()) {
                selected.add(individual);
                i++;
            } else {
                break;
            }
        }

        return new Population<GT>(selected);
    }

}
