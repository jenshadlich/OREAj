package de.jeha.oreaj.genetic.selection.environmental;

import de.jeha.oreaj.genetic.core.Configuration;
import de.jeha.oreaj.genetic.core.Individual;
import de.jeha.oreaj.genetic.core.Population;
import de.jeha.oreaj.genetic.selection.EnvironmentalSelection;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author jenshadlich@googlemail.com
 */
class Best100UniqueSelectionTest {

    @Test
    void test() {
        EnvironmentalSelection<Integer> selection = new Best100UniqueSelection<>(
                Configuration.New().withPopulationMaxSize(3).build()
        );
        List<Individual<Integer>> individuals = new ArrayList<>();
        for (int i = 5; i > 0; i--) {
            individuals.add(new Individual<>(i, i));
            individuals.add(new Individual<>(i, i));
        }

        Population<Integer> population = new Population<>(individuals);
        assertEquals(10, population.size());

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
