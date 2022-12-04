package nl.tubby.aoc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class SectionTest {

    @Test
    void testBuild() {
        var slurper = new SectionSlurper("src/test/resources","puzzle-example-day4.txt");

        var result = slurper.build().collect(Collectors.toList());

        assertEquals(6,result.size());

        var first = result.get(0);
        assertEquals(2,first.getLeft().start());
        assertEquals(4,first.getLeft().end());
        assertEquals(6,first.getRight().start());
        assertEquals(8,first.getRight().end());

        var last = result.get(5);
        assertEquals(2,last.getLeft().start());
        assertEquals(6,last.getLeft().end());
        assertEquals(4,last.getRight().start());
        assertEquals(8,last.getRight().end());
    }

    @ParameterizedTest
    @CsvSource({
            "puzzle-example-day4.txt,2",
            "puzzle-input-day4.txt,511"
    })
    void testFindContainedPairs(String file,int expectedPairCount) {
        var slurper = new SectionSlurper("src/test/resources",file);

        var count = slurper.build()
                .filter(p -> p.getLeft().contains(p.getRight()) || p.getRight().contains(p.getLeft()))
                .count();

        assertEquals(expectedPairCount,count);
    }

    @ParameterizedTest
    @CsvSource({
            "puzzle-example-day4.txt,4",
            "puzzle-input-day4.txt,511"
    })
    void testFindOverlappedPairs(String file,int expectedPairCount) {
        var slurper = new SectionSlurper("src/test/resources",file);

        var count = slurper.build()
                .filter(p -> p.getLeft().overlaps(p.getRight()) || p.getRight().overlaps(p.getLeft()))
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
            "2-6,4-8;false"
    },delimiterString = ";")
    void overlap(String input, boolean overlaps) {
        var pair = SectionSlurper.build(input);

        var result = pair.getLeft().overlaps(pair.getRight()) ||
                pair.getRight().overlaps(pair.getLeft());

        assertEquals(overlaps,result);
    }
}