package nl.tubby.aoc;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.nio.file.Path;
import java.util.List;

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
            "puzzle-example-day3.txt,6,157",
            "puzzle-input-day3.txt,300,7903"// <-- solution part 1
    })
    void testForFile(String file,int expectedCount,int expectedSum) {
        List<Rucksack> rucksacks = new RucksackSlurper()
                .slurp(Path.of("src/test/resources",file))
                .toList();

        assertEquals(expectedCount,rucksacks.size());
        assertEquals(expectedSum,rucksacks.stream().mapToInt(Rucksack::priority).sum());
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
            "puzzle-example-day3.txt,2,70",
            "puzzle-input-day3.txt,100,2548"// <-- solution part 2
    })
    void buildThreeElves(String file, int expectedCount, int expectedPriority) {
        List<ThreeElves> groups = new ThreeElvesSlurper()
                .slurp(Path.of("src/test/resources",file))
                .toList();

        assertEquals(expectedCount,groups.size());
        int totalPriority = groups.stream().mapToInt(ThreeElves::priority).sum();
        assertEquals(expectedPriority,totalPriority);
    }
}
