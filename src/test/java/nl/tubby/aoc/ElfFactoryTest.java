package nl.tubby.aoc;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ElfFactoryTest {

    @Test
    void build() {
        FoodItemSlurper slurper = new FoodItemSlurper("src/test/resources","example.txt");
        ElfFactory elfFactory = new ElfFactory(slurper);

        List<Elf> elves = elfFactory.build();

        assertEquals(5,elves.size());
    }
}
