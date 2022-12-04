package nl.tubby.aoc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RucksackTest {

    @ParameterizedTest
    @CsvSource({
            "vJrwpWtwJgWrhcsFMMfFFhFp,vJrwpWtwJgWr,hcsFMMfFFhFp",
            "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL,jqHRNqRjqzjGDLGL,rsFMfFZSrLrFZsSL"
    })
    void parse(String line,String first,String second) {
        var rucksack = Rucksack.parse(line);

        assertEquals(first,rucksack.compartment1());
        assertEquals(second,rucksack.compartment2());
    }

    @ParameterizedTest
    @CsvSource({
            "vJrwpWtwJgWrhcsFMMfFFhFp,p",
            "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL,L"
    })
    void intersect(String line,String expected) {
        var rucksack = Rucksack.parse(line);

        assertEquals(expected,rucksack.intersect());
    }

    @ParameterizedTest
    @CsvSource({
            "puzzle-example-day3.txt,6"
    })
    void testForFile(String file,int expectedCount) {
        var slurper = new RucksackSlurper("src/test/resources",file);

        List<Rucksack> rucksacks = slurper.slurp().toList();

        assertEquals(expectedCount,rucksacks.size());
    }
}
