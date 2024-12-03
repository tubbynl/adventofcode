package nl.tubby.aoc23;

import nl.tubby.Resource;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class Day5SeedTest {

    @CsvSource({
            "50 98 2",
            "52 50 48"
    })
    @ParameterizedTest
    void testParseMapRange(String raw) {
        var range = Day5Seed.MapRange.parse(raw);

        assertNotNull(range);
    }


    @CsvSource({
            "0,0",
            "1,1",
            "96,96",
            "97,97",
            "98,50",
            "99,51",
            "100,100",
            "101,101"
    })
    @ParameterizedTest
    void testRangeMap(int input,int output) {
        var range = Day5Seed.MapRange.parse("50 98 2");

        assertTrue(range.isPresent());
        assertEquals(output, range.get().map(input));
    }

    @ParameterizedTest
    @CsvSource({
            "aoc-2023-day5-example.txt,35"
    })
    @Disabled("unsolved")
    void puzzlePart1(String file,int minValue) {
        var results = new Day5Seed.SeedMappingSlurper().slurpAndMap(Resource.of(file));

        assertEquals(minValue,results.stream().mapToInt(Integer::intValue).min().orElse(0));
    }
}