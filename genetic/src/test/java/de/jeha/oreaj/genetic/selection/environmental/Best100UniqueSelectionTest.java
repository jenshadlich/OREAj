package de.jeha.oreaj.genetic.selection.environmental;

import de.jeha.oreaj.genetic.core.ConfigurationBuilder;
import de.jeha.oreaj.genetic.core.Individual;
import de.jeha.oreaj.genetic.core.Population;
import de.jeha.oreaj.genetic.selection.EnvironmentalSelection;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

/**
 * @author jenshadlich@googlemail.com
 */
public class Best100UniqueSelectionTest {

    @Test
    public void test() {
        EnvironmentalSelection<Integer> selection = new Best100UniqueSelection<>(
                new ConfigurationBuilder().setPopulationMaxSize(3).build()
        );
        List<Individual<Integer>> individuals = new ArrayList<>();
        for (int i = 5; i > 0; i--) {
            individuals.add(new Individual<>(i, i));
            individuals.add(new Individual<>(i, i));
        }

        Population<Integer> population = new Population<>(individuals);
        Population<Integer> populationAfterSelection = selection.select(population);

        assertEquals(3, populationAfterSelection.size());
        assertEquals(1, populationAfterSelection.best().getGenotype().intValue());
        assertEquals(1, populationAfterSelection.best().getFitness(), 0.e-3);

        List<Individual<Integer>> selectedIndividuals = populationAfterSelection.stream().collect(Collectors.toList());
        assertEquals(1, selectedIndividuals.get(0).getGenotype().intValue());
        assertEquals(2, selectedIndividuals.get(1).getGenotype().intValue());
        assertEquals(3, selectedIndividuals.get(2).getGenotype().intValue());
    }
}
