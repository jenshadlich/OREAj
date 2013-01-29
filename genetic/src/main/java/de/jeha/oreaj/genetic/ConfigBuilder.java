package de.jeha.oreaj.genetic;

public class ConfigBuilder {
	private int maxRuns = 10;
	private int popSize = 100;
	private double threshold = 0.01;

	public Config build() {
		Config c = new Config(maxRuns, popSize, threshold);
		return c;
	}

	public ConfigBuilder setMaxRuns(int maxRuns) {
		this.maxRuns = maxRuns;
		return this;
	}

	public ConfigBuilder setPopSize(int popSize) {
		this.popSize = popSize;
		return this;
	}

	public ConfigBuilder setThreshold(double threshold) {
		this.threshold = threshold;
		return this;
	}
}
