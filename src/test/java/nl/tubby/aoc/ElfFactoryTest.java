package nl.tubby.aoc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

    @ParameterizedTest
    @CsvSource({
            "src/test/resources,example.txt,4,24000",
            "src/test/resources,puzzle-input-day1.txt,238,68442"
    })
    void findTheBest(String dir,String file,int expectedNr,int expectedSum) {
        ElfFactory elfFactory = new ElfFactory(new FoodItemSlurper(dir,file));

        Elf best = elfFactory.build().stream()
                .sorted(Comparator.comparing(Elf::sumCalories).reversed())
                .findFirst().get();

        assertEquals(expectedNr,best.nr());
        assertEquals(expectedSum,best.sumCalories());
    }

}
