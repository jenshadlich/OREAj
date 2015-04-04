package de.jeha.oreaj.main;

import de.jeha.oreaj.genetic.core.Configuration;
import de.jeha.oreaj.genetic.core.ConfigurationBuilder;
import de.jeha.oreaj.genetic.GeneticSolver;
import de.jeha.oreaj.genetic.core.Population;
import de.jeha.oreaj.genetic.selection.environmental.Best100Selection;
import de.jeha.oreaj.genetic.selection.parental.LinearRecombination;
import de.jeha.oreaj.regex.automaton.AutomatonHelper;
import de.jeha.oreaj.regex.crossover.RandomTreeCrossover;
import de.jeha.oreaj.regex.evaluator.RXEvaluate;
import de.jeha.oreaj.regex.generator.RXGenerator;
import de.jeha.oreaj.regex.rx.RX;
import dk.brics.automaton.Automaton;
import dk.brics.automaton.RegExp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Optimization of Regular Expressions using Evolutionary Algorithms. Main program.
 *
 * @author jenshadlich@googlemail.com
 */
public class Orea {

    private static final Logger LOG = LoggerFactory.getLogger(Orea.class);

    /**
     * @param args cmd line arguments (not used)
     */
    public static void main(String... args) {
        //simpleTask1();
        simpleTask2();
        //shuffleTask1();
    }

    // -----------------------------------------------------------------------------------------------------------------

    private static void simpleTask1() {
        final String[] sigma = {"a", "b"};
        final Automaton target = new RegExp("(aa|ba)*").toAutomaton();

        Configuration configuration = new ConfigurationBuilder()
                .setPopulationMaxSize(1000)
                .setMaxRuns(100)
                .setThreshold(0.8)
                .build();

        GeneticSolver<RX> solver = new GeneticSolver<>(
                configuration,
                new RXGenerator(3, sigma),
                new RXEvaluate(target),
                new LinearRecombination<>(new RandomTreeCrossover()),
                new Best100Selection<>(configuration));

        Population<RX> result = solver.evolve();

        printResult(target, result);
    }

    private static void simpleTask2() {
        final String[] sigma = {"a", "b"};
        final Automaton target = new RegExp("(ab|bb)*").toAutomaton();

        Configuration configuration = new ConfigurationBuilder()
                .setPopulationMaxSize(1000)
                .setMaxRuns(100)
                .setThreshold(0.8)
                .build();

        GeneticSolver<RX> solver = new GeneticSolver<>(
                configuration,
                new RXGenerator(3, sigma),
                new RXEvaluate(target),
                new LinearRecombination<>(new RandomTreeCrossover()),
                new Best100Selection<>(configuration));

        Population<RX> result = solver.evolve();

        printResult(target, result);
    }

    private static void shuffleTask1() {
        final String[] sigma = {"a", "b", "c"};
        //final Automaton target = new RegExp("(aa)*$b*").toAutomaton();
        final Automaton target = new RegExp("(ab)$(bc)").toAutomaton(); // bcab + bacb + babc + abbc + abcb

        Configuration configuration = new ConfigurationBuilder()
                .setPopulationMaxSize(1000)
                .setMaxRuns(50)
                .setThreshold(0.8)
                .build();

        GeneticSolver<RX> solver = new GeneticSolver<>(
                configuration,
                new RXGenerator(3, sigma),
                new RXEvaluate(target),
                new LinearRecombination<>(new RandomTreeCrossover()),
                new Best100Selection<>(configuration));

        Population<RX> result = solver.evolve();

        printResult(target, result);
    }

    private static void printResult(Automaton target, Population<RX> result) {
        RX winner = result.best().getGenotype();
        boolean isEquivalent = AutomatonHelper.isEquivalent(target, new RegExp(winner.show()).toAutomaton());

        LOG.info("Winner = '{}'", winner.show());
        LOG.info("Is equivalent? {}",  isEquivalent ? "YES" : "NO");
    }
}
