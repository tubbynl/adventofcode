package nl.tubby.aoc;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class FibonacciTest {

    @Test
    void next() {
        var fibo = new Fibonacci();

        for (int i = 0; i < 10; i++) {
            var next = fibo.next();
            System.err.println(i + " next: " + next);
        }

        System.err.println(fibo);
    }
}