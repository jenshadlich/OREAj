package de.jeha.oreaj.regex.crossover;

import de.jeha.oreaj.genetic.Crossover;
import de.jeha.oreaj.regex.rx.Dot;
import de.jeha.oreaj.regex.rx.RX;
import de.jeha.oreaj.regex.rx.Union;
import de.jeha.oreaj.regex.subtree.Subtree;

import java.util.Random;

public class RXCrossover implements Crossover<RX> {

    private static final Random GENERATOR = new Random();

    @Override
    public RX crossover(RX mom, RX dad) {
        RX stMom = Subtree.randomSubtree(mom);
        RX stDad = Subtree.randomSubtree(dad);

        return randomGlue(stMom, stDad);
    }

    // -----------------------------------------------------------------------------------------------------------------

    private RX randomGlue(RX mom, RX dad) {
        return GENERATOR.nextBoolean()
                ? new Dot(mom, dad)
                : new Union(mom, dad);
    }
}
