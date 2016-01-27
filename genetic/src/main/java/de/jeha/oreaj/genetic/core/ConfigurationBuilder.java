package de.jeha.oreaj.genetic.core;

/**
 * @author jenshadlich@googlemail.com
 */
public class ConfigurationBuilder {

    private int maxRuns = 10;
    private int populationMaxSize = 100;
    private double threshold = 0.01;
    
    public ConfigurationBuilder setMaxRuns(int maxRuns) {
        this.maxRuns = maxRuns;
        return this;
    }

    public ConfigurationBuilder setPopulationMaxSize(int populationMaxSize) {
        this.populationMaxSize = populationMaxSize;
        return this;
    }

    public ConfigurationBuilder setThreshold(double threshold) {
        this.threshold = threshold;
        return this;
    }

    public Configuration build() {
        return new Configuration(maxRuns, populationMaxSize, threshold);
    }

}
