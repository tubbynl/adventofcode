package nl.tubby.aoc22;

import nl.tubby.Resource;
import nl.tubby.Slurper;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * day 3
 */
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
            "puzzle-example-day3.txt,157",
            "puzzle-input-day3.txt,7903"// <-- solution part 1
    })
    void testForFile(String file,int expectedSum) {
        var slurper = new Slurper<>(Rucksack::parse);

        int sum = slurper.sum(Resource.of(file),Rucksack::priority);

        assertEquals(expectedSum,sum);
    }

    @ParameterizedTest
    @CsvSource({
            "a,1",
            "z,26",
            "A,27",
            "Z,52"
    })
    void charToPrority(char ch,int priority) {
        assertEquals(priority,Rucksack.charToPrority(ch));
    }

    @ParameterizedTest
    @CsvSource({
            "puzzle-example-day3.txt,70",
            "puzzle-input-day3.txt,2548"// <-- solution part 2
    })
    void buildThreeElves(String file, int expectedPriority) {
        var slurper = new Slurper<>(new ThreeElvesCollector()::collectRucksacks);

        int sumPriority = slurper.sum(Resource.of(file),ThreeElves::priority);

        assertEquals(expectedPriority,sumPriority);
    }
}
