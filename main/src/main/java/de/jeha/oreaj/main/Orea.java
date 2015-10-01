package de.jeha.oreaj.main;

import de.jeha.oreaj.genetic.GeneticSolver;
import de.jeha.oreaj.genetic.core.Configuration;
import de.jeha.oreaj.genetic.core.ConfigurationBuilder;
import de.jeha.oreaj.genetic.core.Population;
import de.jeha.oreaj.genetic.selection.environmental.Best100UniqueSelection;
import de.jeha.oreaj.genetic.selection.parental.LinearVariation;
import de.jeha.oreaj.regex.Sigma;
import de.jeha.oreaj.regex.automaton.AutomatonHelper;
import de.jeha.oreaj.regex.crossover.RandomTreeCrossover;
import de.jeha.oreaj.regex.evaluator.RXEvaluator;
import de.jeha.oreaj.regex.evaluator.RXEvaluatorWithLength;
import de.jeha.oreaj.regex.generator.RXGenerator;
import de.jeha.oreaj.regex.mutation.*;
import de.jeha.oreaj.regex.rx.RX;
import dk.brics.automaton.Automaton;
import dk.brics.automaton.RegExp;
import dk.brics.automaton.ShuffleOperations;
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
     * TODO: cmd line arguments to control tasks and configuration.
     *
     * @param args cmd line arguments (not used yet)
     */
    public static void main(String... args) {
        //simpleTask1();
        //simpleTask2();
        shuffleTask1();
    }

    // -----------------------------------------------------------------------------------------------------------------

    private static void simpleTask1() {
        final Sigma sigma = new Sigma("a", "b");
        final Automaton target = new RegExp("(aa|ba)*").toAutomaton();

        Configuration configuration = new ConfigurationBuilder()
                .setPopulationMaxSize(1000)
                .setMaxRuns(1000)
                .setThreshold(0.8)
                .build();

        GeneticSolver<RX> solver = new GeneticSolver<>(
                configuration,
                new RXGenerator(3, sigma),
                new RXEvaluatorWithLength(target),
                new LinearVariation<>(
                        new RandomTreeCrossover(),
                        new RandomMutation(
                                new PointMutation(sigma),
                                new StarMutation(),
                                new CollapseSubtreeToRandomTerminalMutation(sigma),
                                new ExpandMutation(sigma)
                        )
                ),
                new Best100UniqueSelection<>(configuration)
        );

        Population<RX> result = solver.evolve();

        printResult(target, result);
    }

    private static void simpleTask2() {
        final Sigma sigma = new Sigma("a", "b");
        final Automaton target = new RegExp("(ab|bb)*").toAutomaton();

        Configuration configuration = new ConfigurationBuilder()
                .setPopulationMaxSize(1000)
                .setMaxRuns(10000)
                .setThreshold(0.1)
                .build();

        GeneticSolver<RX> solver = new GeneticSolver<>(
                configuration,
                new RXGenerator(3, sigma),
                new RXEvaluator(target),
                new LinearVariation<>(
                        new RandomTreeCrossover(),
                        new RandomMutation(
                                new PointMutation(sigma),
                                new CollapseSubtreeToRandomTerminalMutation(sigma)
                        )
                ),
                new Best100UniqueSelection<>(configuration)
        );

        Population<RX> result = solver.evolve();

        printResult(target, result);
    }

    private static void shuffleTask1() {
        final Sigma sigma = new Sigma("a", "b", "c");

        // ab $ bc = bcab + bacb + babc + abbc + abcb
        final Automaton target = ShuffleOperations.shuffle(
                new RegExp("ab").toAutomaton(),
                new RegExp("bc").toAutomaton()
        );

        Configuration configuration = new ConfigurationBuilder()
                .setPopulationMaxSize(1000)
                .setMaxRuns(1000)
                .setThreshold(2.4)
                .build();

        GeneticSolver<RX> solver = new GeneticSolver<>(
                configuration,
                new RXGenerator(3, sigma),
                new RXEvaluatorWithLength(target),
                new LinearVariation<>(
                        new RandomTreeCrossover(),
                        new RandomMutation(
                                new PointMutation(sigma),
                                new CollapseSubtreeToRandomTerminalMutation(sigma),
                                new ExpandMutation(sigma)
                        )
                ),
                new Best100UniqueSelection<>(configuration)
        );

        Population<RX> result = solver.evolve();

        printResult(target, result);
    }

    private static void printResult(Automaton target, Population<RX> result) {
        RX winner = result.best().getGenotype();
        boolean isEquivalent = AutomatonHelper.isEquivalent(target, new RegExp(winner.show()).toAutomaton());

        LOG.info("Winner = '{}'", winner.show());
        LOG.info("Is equivalent? {}", isEquivalent ? "YES" : "NO");
    }
}
