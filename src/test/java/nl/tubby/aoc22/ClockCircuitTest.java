package nl.tubby.aoc22;

import nl.tubby.Resource;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * day 10
 */
class ClockCircuitTest {

    @ParameterizedTest
    @ValueSource(ints = {20,60,100,140,180,220,260})
    void shouldMultiplyByX(int cycle) {
        assertTrue(ClockCircuit.shouldMultiplyByX(cycle));
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,19,21,40,41,59,61})
    void shouldNotMultiplyByX(int cycle) {
        assertFalse(ClockCircuit.shouldMultiplyByX(cycle));
    }

    @ParameterizedTest
    @CsvSource({
            "puzzle-example-day10.txt,0",
            //"puzzle-example-day10-2.txt,13140", FIXME: :)
    })
    void assignment1(String file,int expectedCount) {
        var sum = ClockCircuit.slurp(Resource.of(file));

        assertEquals(expectedCount,sum);
    }
}