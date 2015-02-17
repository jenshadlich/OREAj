package de.jeha.oreaj.regex.generator;

import de.jeha.oreaj.genetic.Generator;
import de.jeha.oreaj.regex.rx.*;

import java.util.Random;

public class RXGenerator implements Generator<RX> {

    private static final Random GENERATOR = new Random();
    private int depth;
    private String[] sigma;

    /**
     * @param depth depth of regular expression tree
     * @param sigma input alphabet
     */
    public RXGenerator(int depth, String[] sigma) {
        this.depth = depth;
        this.sigma = sigma;
    }

    @Override
    public RX generate() {
        return full(this.depth, this.sigma);
    }

    // -----------------------------------------------------------------------------------------------------------------

    private RX full(int d, String[] sigma) {
        if (d > 0) {
            // random Operation
            switch (GENERATOR.nextInt(3)) {
                case 0:
                    return new Dot(full(d - 1, sigma), full(d - 1, sigma));
                case 1:
                    return new Union(full(d - 1, sigma), full(d - 1, sigma));
                case 2:
                default:
                    return new Star(full(d - 1, sigma));
            }
        } else {
            return randomLetter();
        }
    }

    private Letter randomLetter() {
        return new Letter(sigma[GENERATOR.nextInt(sigma.length)]);
    }

}
