package nl.tubby.aoc;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class FibonacciTest {
    @Test
    void next() {
        var fibo = new Fibonacci();

        Stream.of(1, 2, 3, 5, 8, 13, 21, 34, 55, 89)
            .forEach(expectedValue -> {
                assertEquals(expectedValue,fibo.next());
            });
    }
}