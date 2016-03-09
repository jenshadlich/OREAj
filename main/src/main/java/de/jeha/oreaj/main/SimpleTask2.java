package de.jeha.oreaj.main;

import de.jeha.oreaj.genetic.GeneticSolver;
import de.jeha.oreaj.genetic.core.Configuration;
import de.jeha.oreaj.genetic.core.Population;
import de.jeha.oreaj.genetic.core.RandomPopulationInitializer;
import de.jeha.oreaj.genetic.selection.environmental.Best100UniqueSelection;
import de.jeha.oreaj.genetic.selection.parental.LinearVariation;
import de.jeha.oreaj.regex.Sigma;
import de.jeha.oreaj.regex.crossover.RandomTreeCrossover;
import de.jeha.oreaj.regex.evaluator.RXEvaluator;
import de.jeha.oreaj.regex.generator.RXGenerator;
import de.jeha.oreaj.regex.mutation.CollapseSubtreeToRandomTerminalMutation;
import de.jeha.oreaj.regex.mutation.PointMutation;
import de.jeha.oreaj.regex.mutation.RandomMutation;
import de.jeha.oreaj.regex.rx.RX;
import dk.brics.automaton.Automaton;
import dk.brics.automaton.RegExp;

/**
 * @author jenshadlich@googlemail.com
 */
public class SimpleTask2 extends AbstractTask {

    @Override
    public void run() {
        final Sigma sigma = new Sigma("a", "b");
        final Automaton target = new RegExp("(ab|bb)*").toAutomaton();

        Configuration configuration = Configuration.New()
                .withPopulationMaxSize(1000)
                .withMaxRuns(10000)
                .withThreshold(0.1)
                .build();

        GeneticSolver<RX> solver = new GeneticSolver<>(
                configuration,
                new RandomPopulationInitializer<>(new RXGenerator(3, sigma)),
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

}
