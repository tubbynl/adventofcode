package nl.tubby.aoc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SectionTest {

    @Test
    void testBuild() {
        var result = new SectionSlurper()
                .slurp(Path.of("src/test/resources","puzzle-example-day4.txt"))
                .collect(Collectors.toList());

        assertEquals(6,result.size());

        var first = result.get(0);
        assertEquals(2, first.left().start());
        assertEquals(4,first.left().end());
        assertEquals(6,first.right().start());
        assertEquals(8,first.right().end());

        var last = result.get(5);
        assertEquals(2,last.left().start());
        assertEquals(6,last.left().end());
        assertEquals(4,last.right().start());
        assertEquals(8,last.right().end());
    }

    @Test
    void set() {
        assertEquals(Set.of(2,3,4),new Section(2,4).set());
    }

    @ParameterizedTest
    @CsvSource({
            "puzzle-example-day4.txt,2",
            "puzzle-input-day4.txt,511"
    })
    void testFindContainedPairs(String file,int expectedPairCount) {
        var count = new SectionSlurper()
                .slurp(Path.of("src/test/resources",file))
                .filter(PairOfSections::contains)
                .count();

        assertEquals(expectedPairCount,count);
    }

    @ParameterizedTest
    @CsvSource({
            "puzzle-example-day4.txt,4",
            "puzzle-input-day4.txt,821"
    })
    void testFindOverlappedPairs(String file,int expectedPairCount) {
        var count = new SectionSlurper()
                .slurp(Path.of("src/test/resources",file))
                .filter(PairOfSections::overlaps)
                .count();

        assertEquals(expectedPairCount,count);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "5-7,7-9;true",//5-7,7-9 overlap on 7
            "2-8,3-7;true",
            "6-6,4-6;true",
            "2-6,4-8;true",
            "5-7,3-5;true",//5-7,3-5 overlap on 5
            "2-4,6-8;false",
            "2-3,4-5;false",
            "2-6,4-8;true"
    },delimiterString = ";")
    void overlap(String input, boolean overlaps) {
        var pair = PairOfSections.parse(input);

        assertEquals(overlaps,pair.overlaps());
    }
}