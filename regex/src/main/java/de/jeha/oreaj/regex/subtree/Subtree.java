package de.jeha.oreaj.regex.subtree;

import de.jeha.oreaj.regex.rx.RX;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Subtree {

    public static List<RX> subtrees(RX root) {
        List<RX> subtrees = new ArrayList<RX>();
        for (RX i : root) {
            subtrees.add(i);
        }
        return subtrees;
    }

    public static RX randomSubtree(RX root) {
        Random generator = new Random();
        List<RX> subtrees = subtrees(root);
        return subtrees.get(generator.nextInt(subtrees.size() - 1));
    }

}
