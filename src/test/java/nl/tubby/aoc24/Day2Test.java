package nl.tubby.aoc24;

import nl.tubby.Resource;
import nl.tubby.Slurper;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class Day2Test {

    @ParameterizedTest
    @CsvSource({
            "1,3,increasing,2",
            "4,1,decreasing,3",
            "1,1,none,0"
    })
    void levelChain(int a, int b, Day2.LevelChain.Direction direction, int difference) {
        var chain = new Day2.LevelChain(a,b);

        assertEquals(direction,chain.direction());
        assertEquals(difference,chain.difference());
    }


    @ParameterizedTest
    @CsvSource({
            "day2-example.txt,2",
            "day2-input.txt,242"
    })
    void part1(Resource resource, int expected) {
        var slurper = new Slurper<>(Day2.Report::parse,Day2.Report::isSafe);

        assertEquals(expected,slurper.count(resource));
    }
}