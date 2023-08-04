package nl.tubby.aoc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigInteger;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class FibonacciTest {

    @Test
    void getLastGetSecondLast() {
        var fibo = new Fibonacci();

        assertEquals(BigInteger.ONE,fibo.getLast());
        assertEquals(BigInteger.ZERO,fibo.getSecondLast());
    }

    @Test
    void sumLastTwo() {
        var fibo = new Fibonacci();

        assertEquals(BigInteger.ONE,fibo.sumLastTwo());
    }

    @Test
    void next() {
        var fibo = new Fibonacci();

        Stream.of(1, 2, 3, 5, 8, 13, 21, 34, 55, 89)
            .forEach(expectedValue -> {
                assertEquals(BigInteger.valueOf(expectedValue),fibo.next());
            });
    }

    @ParameterizedTest
    @CsvSource({
            "12,89",
            "22,10946",
            "35,5702887",
            "50,7778742049"
    })
    void nextWithProvidedIterations(int size,BigInteger expectedValue) {
        var fibo = new Fibonacci();
        var iterations = size-fibo.size();

        for(int i=0;i<iterations;i++) {
            fibo.next();
        }

        assertEquals(size,fibo.size());
        assertEquals(expectedValue,fibo.getLast());
    }
}