package nl.tubby.aoc24;

import nl.tubby.Resource;
import nl.tubby.Slurper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class Day3ProgramTest {

    @ParameterizedTest
    @CsvSource({
            "day3-example.txt,161"
    })
    void part1(Resource resource, int expected) {
        var slurper = new Slurper<>(Day3Program::parse);

        assertEquals(expected,slurper.sum(resource,Day3Program.Instructions::sum));
    }
}