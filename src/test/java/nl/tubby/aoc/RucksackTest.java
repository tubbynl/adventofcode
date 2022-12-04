package nl.tubby.aoc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RucksackTest {

    @Test
    void parse() {
        var rucksack = Rucksack.parse("vJrwpWtwJgWrhcsFMMfFFhFp");

        assertEquals("vJrwpWtwJgWr",rucksack.compartment1());
        assertEquals("hcsFMMfFFhFp",rucksack.compartment2());
    }
}
