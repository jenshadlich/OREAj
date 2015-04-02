package de.jeha.oreaj.genetic.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Population<GT> implements Iterable<Individual<GT>> {

    private static final Logger LOG = LoggerFactory.getLogger(Population.class);

    private final List<Individual<GT>> individuals;

    public Population(List<Individual<GT>> individuals) {
        super();
        this.individuals = individuals;
    }

    public int size() {
        return individuals.size();
    }

    public Individual<GT> best() {
        return individuals.stream().sorted().findFirst().orElse(null);
    }

    public void join(List<Individual<GT>> individuals) {
        this.individuals.addAll(individuals);
    }

    @Override
    public Iterator<Individual<GT>> iterator() {

        final Iterator<Individual<GT>> it = this.individuals.iterator();

        return new Iterator<Individual<GT>>() {

            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public Individual<GT> next() {
                return it.next();
            }

            @Override
            public void remove() {
                it.remove();
            }
        };
    }

    public Stream<Individual<GT>> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

}
