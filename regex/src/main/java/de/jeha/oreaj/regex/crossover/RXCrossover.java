package de.jeha.oreaj.regex.crossover;

import de.jeha.oreaj.genetic.Crossover;
import de.jeha.oreaj.regex.rx.Dot;
import de.jeha.oreaj.regex.rx.RX;
import de.jeha.oreaj.regex.rx.Union;
import de.jeha.oreaj.regex.subtree.Subtree;

import java.util.Random;

public class RXCrossover implements Crossover<RX> {

    @Override
    public RX crossover(RX mom, RX dad) {
        RX stMom = Subtree.randomSubtree(mom);
        RX stDad = Subtree.randomSubtree(dad);

        return randomGlue(stMom, stDad);
    }

    private RX randomGlue(RX mom, RX dad) {
        Random generator = new Random();
        int r = generator.nextInt(1);
        switch (r) {
            case 0:
                return new Dot(mom, dad);
            default:
                return new Union(mom, dad);
        }
    }
}
