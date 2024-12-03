package nl.tubby.aoc24;

import nl.tubby.Resource;
import nl.tubby.Slurper;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class Day2Test {

    @ParameterizedTest
    @CsvSource({
            "day2-example.txt,2",
            "day2-input.txt,53"
    })
    void part1(Resource resource, int expected) {
        var slurper = new Slurper<>(Day2.Report::new,Day2.Report::isSafe);

        assertEquals(expected,slurper.count(resource));
    }
}