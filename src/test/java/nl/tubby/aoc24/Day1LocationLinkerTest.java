package nl.tubby.aoc24;

import nl.tubby.Resource;
import nl.tubby.Slurper;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day1LocationLinkerTest {

    @Test
    void totalDistance() {
        var slurper = new Slurper<>(Day1LocationLinker::parse);

        var result = slurper.list(Resource.of("day1-example.txt"));
        var right = result.stream().mapToInt(Day1LocationLinker.Pair::right).sorted().iterator();

        var totalDistance = result.stream()
                .mapToInt(Day1LocationLinker.Pair::left)
                .sorted()
                .mapToObj(left -> new Day1LocationLinker.Pair(left,right.nextInt()))
                .mapToInt(Day1LocationLinker.Pair::distance)
                .sum();

        assertEquals(11,totalDistance);
    }
}