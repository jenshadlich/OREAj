package de.jeha.oreaj.regex.generator;

import de.jeha.oreaj.genetic.Generator;
import de.jeha.oreaj.regex.rx.*;

import java.util.Random;

public class RXGenerator implements Generator<RX> {

    private Random randomGenerator = null;
    private int depth;
    private String[] sigma;

    public RXGenerator(int depth, String[] sigma) {
        this.randomGenerator = new Random();
        this.depth = depth;
        this.sigma = sigma;
    }

    @Override
    public RX generate() {
        return full(this.depth, this.sigma);
    }

    // -------------------------------------------------------------------------
    //
    // -------------------------------------------------------------------------

    private RX full(int d, String[] sigma) {
        if (d > 0) {
            // random Operation
            switch (this.randomGenerator.nextInt(3)) {
                case 0:
                    return new Dot(full(d - 1, sigma), full(d - 1, sigma));
                case 1:
                    return new Union(full(d - 1, sigma), full(d - 1, sigma));
                default:
                    return new Star(full(d - 1, sigma));
            }
        } else {
            // random Letter
            return new Letter(sigma[this.randomGenerator.nextInt(sigma.length)]);
        }
    }

}
