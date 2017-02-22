package de.jeha.oreaj.main;

import de.jeha.oreaj.genetic.GeneticSolver;
import de.jeha.oreaj.genetic.core.Configuration;
import de.jeha.oreaj.genetic.core.Population;
import de.jeha.oreaj.genetic.core.RandomPopulationInitializer;
import de.jeha.oreaj.genetic.selection.environmental.Best100UniqueSelection;
import de.jeha.oreaj.genetic.selection.parental.LinearVariation;
import de.jeha.oreaj.regex.Sigma;
import de.jeha.oreaj.regex.crossover.RandomTreeCrossover;
import de.jeha.oreaj.regex.evaluator.RXEvaluatorWithLength;
import de.jeha.oreaj.regex.generator.RXGenerator;
import de.jeha.oreaj.regex.mutation.*;
import de.jeha.oreaj.regex.rx.RX;
import dk.brics.automaton.Automaton;
import dk.brics.automaton.RegExp;

/**
 * @author jenshadlich@googlemail.com
 */
public class SimpleTask1 extends AbstractTask {

    @Override
    public void run() {
        final Sigma sigma = new Sigma("a", "b");
        final Automaton target = new RegExp("(aa|ba)*").toAutomaton();

        Configuration configuration = Configuration.New()
                .withPopulationMaxSize(1_000)
                .withMaxRuns(1_000)
                .withThreshold(0.8)
                .build();

        GeneticSolver<RX> solver = new GeneticSolver<>(
                configuration,
                new RandomPopulationInitializer<>(new RXGenerator(3, sigma)),
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

}
