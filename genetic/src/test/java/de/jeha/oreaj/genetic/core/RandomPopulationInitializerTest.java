package de.jeha.oreaj.genetic.core;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author jenshadlich@googlemail.com
 */
class RandomPopulationInitializerTest {

    @Test
    void test() {
        PopulationInitializer<String> initializer = new RandomPopulationInitializer<>(() -> "1");

        List<String> result = initializer.initialize(3);

        assertEquals(3, result.size());
        assertEquals("1", result.get(0));
        assertEquals("1", result.get(1));
        assertEquals("1", result.get(2));
    }

}
