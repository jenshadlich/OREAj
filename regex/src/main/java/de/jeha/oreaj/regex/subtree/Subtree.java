package de.jeha.oreaj.regex.subtree;

import de.jeha.oreaj.regex.rx.RX;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Subtree {

    private static final Random GENERATOR = new Random();

    public static List<RX> subtrees(RX root) {
        List<RX> subtrees = new ArrayList<>();
        for (RX rx : root) {
            subtrees.add(rx);
        }
        return subtrees;
    }

    public static RX randomSubtree(RX root) {
        List<RX> subtrees = subtrees(root);
        return subtrees.get(GENERATOR.nextInt(subtrees.size() - 1));
    }

}
