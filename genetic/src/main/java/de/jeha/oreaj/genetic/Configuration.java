package de.jeha.oreaj.genetic;

public class Configuration {
    final int maxRuns;
    final int popSize;
    final double threshold;

    Configuration(int maxRuns, int popSize, double threshold) {
        super();
        this.maxRuns = maxRuns;
        this.popSize = popSize;
        this.threshold = threshold;
    }

    public int getMaxRuns() {
        return maxRuns;
    }

    public int getPopSize() {
        return popSize;
    }

    public double getThreshold() {
        return threshold;
    }

}
