package nl.tubby.aoc;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class DataStreamTest {


    @ParameterizedTest
    @CsvSource({
            "puzzle-example-day6.txt,12"
    })
    void testFinding(String file,int expectedLocation) {
        var location = new DataStream().first(Path.of(file));

        assertEquals(expectedLocation,location);
    }
}