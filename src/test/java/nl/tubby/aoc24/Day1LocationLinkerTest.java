package nl.tubby.aoc24;

import nl.tubby.Resource;
import nl.tubby.Slurper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day1LocationLinkerTest {

    @ParameterizedTest
    @CsvSource({
            "day1-example.txt,11"
    })
    void totalDistance(Resource file,int expected) {
        var slurper = new Slurper<>(Day1LocationLinker::parse);

        var result = slurper.list(file);
        var right = result.stream().mapToInt(Day1LocationLinker.Pair::right).sorted().iterator();

        var totalDistance = result.stream()
                .mapToInt(Day1LocationLinker.Pair::left)
                .sorted()
                .mapToObj(left -> new Day1LocationLinker.Pair(left,right.nextInt()))
                .mapToInt(Day1LocationLinker.Pair::distance)
                .sum();

        assertEquals(expected,totalDistance);
    }
}