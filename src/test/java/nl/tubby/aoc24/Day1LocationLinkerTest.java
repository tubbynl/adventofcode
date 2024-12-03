package nl.tubby.aoc24;

import nl.tubby.Resource;
import nl.tubby.Slurper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day1LocationLinkerTest {

    @ParameterizedTest
    @CsvSource({
            "day1-example.txt,11",
            "day1-input.txt,2176849"
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

    @ParameterizedTest
    @CsvSource({
            "day1-example.txt,31",
            "day1-input.txt,23384288"
    })
    void similarityScore(Resource file,long expected) {
        var slurper = new Slurper<>(Day1LocationLinker::parse);

        var result = slurper.list(file);
        var rightCounts = result.stream()
                .map(Day1LocationLinker.Pair::right)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        var similarity = result.stream()
                .map(Day1LocationLinker.Pair::left)
                .mapToLong(id -> id * rightCounts.getOrDefault(id,0L))
                .sum();

        assertEquals(expected,similarity);
    }
}