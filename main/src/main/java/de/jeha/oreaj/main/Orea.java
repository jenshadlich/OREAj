package de.jeha.oreaj.main;

import de.jeha.oreaj.genetic.Configuration;
import de.jeha.oreaj.genetic.ConfigurationBuilder;
import de.jeha.oreaj.genetic.GeneticSolver;
import de.jeha.oreaj.genetic.Population;
import de.jeha.oreaj.regex.crossover.RXCrossover;
import de.jeha.oreaj.regex.eval.RXEvaluate;
import de.jeha.oreaj.regex.generator.RXGenerator;
import de.jeha.oreaj.regex.rx.RX;
import dk.brics.automaton.Automaton;
import dk.brics.automaton.RegExp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class Orea {

    private static final Logger LOG = LoggerFactory.getLogger(Orea.class);

    /**
     * @param args
     */
    public static void main(String... args) {
        simpleTask1();
        //shuffleTask1();
    }

    // -----------------------------------------------------------------------------------------------------------------

    private static void simpleTask1() {
        final String[] sigma = {"a", "b", "c"};
        final Automaton target = new RegExp("(aa|ba)*").toAutomaton();

        Configuration configuration = new ConfigurationBuilder()
                .setPopSize(1000)
                .setMaxRuns(100)
                .setThreshold(0.8)
                .build();

        GeneticSolver<RX> solver = new GeneticSolver<>(
                configuration,
                new RXGenerator(3, sigma),
                new RXEvaluate(target),
                new RXCrossover());

        Population<RX> result = solver.evolve();

        LOG.info("Winner = '{}'", result.best().getGenotype().show());
    }

    private static void simpleTask2() {
        final String[] sigma = {"a", "b"};
        final Automaton target = new RegExp("(ab|bb)*").toAutomaton();

        Configuration configuration = new ConfigurationBuilder()
                .setPopSize(1000)
                .setMaxRuns(100)
                .setThreshold(0.8)
                .build();

        GeneticSolver<RX> solver = new GeneticSolver<>(
                configuration,
                new RXGenerator(3, sigma),
                new RXEvaluate(target),
                new RXCrossover());

        Population<RX> result = solver.evolve();

        LOG.info("Winner = '{}'", result.best().getGenotype().show());
    }

    private static void shuffleTask1() {
        final String[] sigma = {"a", "b"};
        final Automaton target = new RegExp("(aa)*$b*").toAutomaton();

        Configuration configuration = new ConfigurationBuilder().setPopSize(1000).setMaxRuns(100).setThreshold(0.8).build();

        GeneticSolver<RX> solver = new GeneticSolver<>(
                configuration,
                new RXGenerator(3, sigma),
                new RXEvaluate(target),
                new RXCrossover());

        Population<RX> result = solver.evolve();

        LOG.info("Winner = '{}'", result.best().getGenotype().show());
    }

}
