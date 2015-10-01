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
        return full(this.depth);
    }

    // -----------------------------------------------------------------------------------------------------------------

    private RX full(int depth) {
        if (depth > 0) {
            // random Operation
            switch (GENERATOR.nextInt(3)) {
                case 0:
                    return new Dot(full(depth - 1), full(depth - 1));
                case 1:
                    return new Union(full(depth - 1), full(depth - 1));
                case 2:
                default:
                    return new Star(full(depth - 1));
            }
        } else {
            return randomLetter();
        }
    }

    private Letter randomLetter() {
        return new Letter(sigma.random());
    }

}
