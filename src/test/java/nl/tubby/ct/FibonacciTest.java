package nl.tubby.ct;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FibonacciTest {
    static final List<BigInteger> FIBONACCI = IntStream.of(0,1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89)
            .mapToObj(BigInteger::valueOf)
            .toList();

    @Test
    void testConstructorAndHelpers() {
        var fibo = new Fibonacci();

        assertEquals(BigInteger.ONE,fibo.getLast());
        assertEquals(BigInteger.ZERO,fibo.getSecondLast());
        assertEquals(BigInteger.ONE,fibo.sumLastTwo());
    }

    @Test
    void nextTwelve() {
        var fibo = new Fibonacci();

        fibo.next(12);

        assertEquals(FIBONACCI,fibo);
    }

    @ParameterizedTest
    @CsvSource({
            "2,1",
            "12,89",
            "22,10946",
            "35,5702887",
            "50,7778742049",
            "100,218922995834555169026",
            "1000,26863810024485359386146727202142923967616609318986952340123175997617981700247881689338369654483356564191827856161443356312976673642210350324634850410377680367334151172899169723197082763985615764450078474174626"
    })
    void nextWithProvidedIterations(int size,BigInteger expectedValue) {
        var fibo = new Fibonacci();

        fibo.next(size);

        assertEquals(size,fibo.size());
        assertEquals(expectedValue,fibo.getLast());
    }
}