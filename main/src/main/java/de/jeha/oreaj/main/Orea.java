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

/**
 *
 */
public class Orea {

    /**
     * @param args
     */
    public static void main(String[] args) {
        simpleTask2();
    }

    // -----------------------------------------------------------------------------------------------------------------

    private static void simpleTask1() {
        String[] sigma = {"a", "b", "c"};
        Automaton target = new RegExp("(aa|ba)*").toAutomaton();

        Configuration c = new ConfigurationBuilder().setPopSize(1000).setMaxRuns(50).setThreshold(0).build();

        GeneticSolver<RX> g = new GeneticSolver<>(c, new RXGenerator(3, sigma), new RXEvaluate(target), new RXCrossover());
        Population<RX> result = g.evolve();

        System.out.println("And the winner is = " + result.best().getGenotype().show());
    }

    private static void simpleTask2() {
        String[] sigma = {"a", "b"};
        Automaton target = new RegExp("(ab|bb)*").toAutomaton();

        Configuration c = new ConfigurationBuilder().setPopSize(1000).setMaxRuns(1000).setThreshold(0.8).build();

        GeneticSolver<RX> g = new GeneticSolver<>(c, new RXGenerator(3, sigma), new RXEvaluate(target), new RXCrossover());
        Population<RX> result = g.evolve();

        System.out.println("And the winner is = " + result.best().getGenotype().show());
    }

}
