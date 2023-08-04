package nl.tubby.aoc;

import java.util.ArrayList;
import java.util.List;

public class Fibonacci extends ArrayList<Integer> {
    public Fibonacci() {
        super();
        add(0);
        add(1);
    }

    Integer getLast() {
        return get(size()-1);
    }

    Integer getSecondLast() {
        return get(size()-2);
    }

    Integer sumLastTwo() {
        return getSecondLast()+getLast();
    }

    Integer next() {
        int nextValue = sumLastTwo();
        add(nextValue);
        return nextValue;
    }
}
