package nl.tubby.aoc;

import java.util.ArrayList;

public class Fibonacci extends ArrayList<Integer> {
    public Fibonacci() {
        super();
        add(0);
        add(1);
    }

    Integer next() {
        int index = size();
        int nextValue = get(index-1)+get(index-2);
        add(nextValue);
        return nextValue;
    }
}
