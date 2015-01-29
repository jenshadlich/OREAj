package de.jeha.oreaj.genetic;

public class Configuration {

    private final int maxRuns;
    private final int populationSize;
    private final double threshold;

    Configuration(int maxRuns, int populationSize, double threshold) {
        this.maxRuns = maxRuns;
        this.populationSize = populationSize;
        this.threshold = threshold;
    }

    public int getMaxRuns() {
        return maxRuns;
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public double getThreshold() {
        return threshold;
    }

}
