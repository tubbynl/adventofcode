package nl.tubby.aoc;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class ClockCircuitTest {

    @ParameterizedTest
    @CsvSource({
            "puzzle-example-day10.txt,-1",
    })
    void assignment1(String file,int expectedCount) {
        var circuit = ClockCircuit.slurp(Path.of(file));

        assertEquals(expectedCount,circuit.x());
    }
}