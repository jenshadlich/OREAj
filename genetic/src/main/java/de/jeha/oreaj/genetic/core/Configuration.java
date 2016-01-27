package de.jeha.oreaj.genetic.core;

/**
 * @author jenshadlich@googlemail.com
 */
public class Configuration {

    private final int maxRuns;
    private final int populationMaxSize;
    private final double threshold;

    Configuration(int maxRuns, int populationMaxSize, double threshold) {
        this.maxRuns = maxRuns;
        this.populationMaxSize = populationMaxSize;
        this.threshold = threshold;
    }

    public int getMaxRuns() {
        return maxRuns;
    }

    public int getPopulationMaxSize() {
        return populationMaxSize;
    }

    public double getThreshold() {
        return threshold;
    }

}
