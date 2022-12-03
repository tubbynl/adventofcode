package nl.tubby.aoc;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ElfFactoryTest {

    @Test
    void build() {
        FoodItemSlurper slurper = new FoodItemSlurper("src/test/resources","example.txt");
        ElfFactory elfFactory = new ElfFactory(slurper);

        List<Elf> elves = elfFactory.build();

        assertEquals(5,elves.size());
        assertEquals(1,elves.get(0).nr());
        assertEquals(1000,elves.get(0).sumCalories());

        assertEquals(2,elves.get(1).nr());
        assertEquals(2000,elves.get(1).sumCalories());
    }

    @Test
    void findTheBest() {
        FoodItemSlurper slurper = new FoodItemSlurper("src/test/resources","example.txt");
        ElfFactory elfFactory = new ElfFactory(slurper);

        Elf best = elfFactory.build().stream()
                .sorted(Comparator.comparing(Elf::sumCalories))
                .findFirst().get();

        assertEquals(4,best.nr());
    }
}
