package nl.tubby.aoc;

import java.math.BigInteger;
import java.util.ArrayList;

public class Fibonacci extends ArrayList<BigInteger> {
    public Fibonacci() {
        super();
        add(BigInteger.ZERO);
        add(BigInteger.ONE);
    }

    BigInteger getLast() {
        return get(size()-1);
    }

    BigInteger getSecondLast() {
        return get(size()-2);
    }

    BigInteger sumLastTwo() {
        return getSecondLast().add(getLast());
    }

    BigInteger next() {
        BigInteger nextValue = sumLastTwo();
        add(nextValue);
        return nextValue;
    }
}
