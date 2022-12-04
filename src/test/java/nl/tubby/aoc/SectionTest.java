package nl.tubby.aoc;

import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class SectionTest {

    @Test
    void testBuild() {
        var slurper = new SectionSlurper("src/test/resources","puzzle-example-day4.txt");

        var result = slurper.build().collect(Collectors.toList());

        assertEquals(6,result.size());

        assertEquals(2,result.get(0).getLeft().start());
        assertEquals(4,result.get(0).getLeft().end());
        assertEquals(6,result.get(0).getRight().start());
        assertEquals(8,result.get(0).getRight().end());
    }
}