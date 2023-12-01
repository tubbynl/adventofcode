package nl.tubby.aoc22;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * day 4
 */
class SectionTest {
    @Test
    void testBuild() {
        var result = new Slurper<>(PairOfSections::parse)
                .list(Path.of("puzzle-example-day4.txt"));

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
            "puzzle-input-day4.txt,511"// <-- solution part 1
    })
    void testFindContainedPairs(String file,int expectedPairCount) {
        var slurper = new Slurper<>(PairOfSections::parse, PairOfSections::contains);

        assertEquals(expectedPairCount,slurper.count(Path.of(file)));
    }

    @ParameterizedTest
    @CsvSource({
            "puzzle-example-day4.txt,4",
            "puzzle-input-day4.txt,821"// <-- solution part 2
    })
    void testFindOverlappedPairs(String file,int expectedPairCount) {
        var slurper = new Slurper<>(PairOfSections::parse, PairOfSections::overlaps);

        assertEquals(expectedPairCount,slurper.count(Path.of(file)));
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