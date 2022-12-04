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
}