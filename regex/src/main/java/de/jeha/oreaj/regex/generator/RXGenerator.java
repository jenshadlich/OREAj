package de.jeha.oreaj.regex.generator;

import de.jeha.oreaj.genetic.core.Generator;
import de.jeha.oreaj.regex.Sigma;
import de.jeha.oreaj.regex.rx.*;

import java.util.Random;

public class RXGenerator implements Generator<RX> {

    private static final Random GENERATOR = new Random();

    private final int depth;
    private final Sigma sigma;

    /**
     * @param depth depth of regular expression tree
     * @param sigma input alphabet
     */
    public RXGenerator(int depth, Sigma sigma) {
        this.depth = depth;
        this.sigma = sigma;
    }

    @Override
    public RX generate() {
        return generateRandomRXTree(this.depth);
    }

    // -----------------------------------------------------------------------------------------------------------------

    private RX generateRandomRXTree(int depth) {
        if (depth > 0) {
            // random Operation
            switch (GENERATOR.nextInt(3)) {
                case 0:
                    return new Dot(generateRandomRXTree(depth - 1), generateRandomRXTree(depth - 1));
                case 1:
                    return new Union(generateRandomRXTree(depth - 1), generateRandomRXTree(depth - 1));
                case 2:
                default:
                    return new Star(generateRandomRXTree(depth - 1));
            }
        } else {
            return new Letter(sigma.random());
        }
    }

}
