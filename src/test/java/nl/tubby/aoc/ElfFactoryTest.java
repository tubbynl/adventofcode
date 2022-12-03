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
        Elf first = elves.get(0);
        assertEquals(1,first.nr());
        assertEquals(6000,first.sumCalories());

        Elf second = elves.get(1);
        assertEquals(2,second.nr());
        assertEquals(4000,second.sumCalories());
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
