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
            "vJrwpWtwJgWrhcsFMMfFFhFp,p,16",
            "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL,L,38",
            "PmmdzqPrVvPwwTWBwg,P,42",
            "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn,v,22",
            "ttgJtRGJQctTZtZT,t,20",
            "CrZsJsPPZsGzwwsLwLmpwMDw,s,19"
    })
    void intersectAndPriority(String line,String expected, int expectedPrority) {
        var rucksack = Rucksack.parse(line);

        assertEquals(expected,rucksack.intersect());
        assertEquals(expectedPrority,rucksack.priority());
    }

    @ParameterizedTest
    @CsvSource({
            "puzzle-example-day3.txt,6,157"
    })
    void testForFile(String file,int expectedCount,int expectedSum) {
        var slurper = new RucksackSlurper("src/test/resources",file);

        List<Rucksack> rucksacks = slurper.slurp().toList();

        assertEquals(expectedCount,rucksacks.size());
        assertEquals(expectedSum,rucksacks.stream().mapToInt(Rucksack::priority).sum());
    }
}
