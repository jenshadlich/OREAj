package de.jeha.oreaj.genetic;

public class ConfigurationBuilder {

    private int maxRuns = 10;
    private int popSize = 100;
    private double threshold = 0.01;
    
    public ConfigurationBuilder setMaxRuns(int maxRuns) {
        this.maxRuns = maxRuns;
        return this;
    }

    public ConfigurationBuilder setPopSize(int popSize) {
        this.popSize = popSize;
        return this;
    }

    public ConfigurationBuilder setThreshold(double threshold) {
        this.threshold = threshold;
        return this;
    }

    public Configuration build() {
        return new Configuration(maxRuns, popSize, threshold);
    }

}
