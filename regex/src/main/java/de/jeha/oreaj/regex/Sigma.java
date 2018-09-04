package de.jeha.oreaj.regex;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author jenshadlich@googlemail.com
 */
public class Sigma {

    private static final Random GENERATOR = new Random();

    private final String[] sigma;
    private final List<String> sigmaList;

    public Sigma(String... sigma) {
        if (ArrayUtils.isEmpty(sigma)) {
            throw new IllegalArgumentException("sigma must not be empty");
        }
        this.sigma = sigma;
        this.sigmaList = Arrays.asList(sigma);
    }

    public String random() {
        return sigma[GENERATOR.nextInt(sigma.length)];
    }

    public List<String> asList() {
        return sigmaList;
    }

    public int size() {
        return sigma.length;
    }

}
