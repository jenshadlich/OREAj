package de.jeha.oreaj.regex;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author jenshadlich@googlemail.com
 */
public class Sigma {

    private static final Random GENERATOR = new Random();

    private final String[] sigma;

    public Sigma(String... sigma) {
        if (sigma == null || sigma.length == 0) {
            throw new IllegalArgumentException("sigma must not be empty");
        }
        this.sigma = sigma;
    }

    public String random() {
        return sigma[GENERATOR.nextInt(sigma.length)];
    }

    public List<String> asList() {
        return Arrays.asList(sigma);
    }

    public int size() {
        return sigma.length;
    }

}
