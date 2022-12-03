package nl.tubby.aoc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ElfFactoryTest {

    @Test
    void build() {
        ElfFactory elfFactory = new ElfFactory("src/test/resources", "puzzle-example-day1.txt");

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
            "src/test/resources,puzzle-example-day1.txt,24000",
            "src/test/resources,puzzle-input-day1.txt,68442",
            "src/test/resources,aoc_2022_day01_large_input.txt,184028272"
    })
    void findTheBest(String dir,String file,int expectedSum) {
        ElfFactory elfFactory = new ElfFactory(dir,file);

        Optional<Integer> best = elfFactory.build().stream()
                .map(Elf::sumCalories)
                .max(Integer::compareTo);

        assertEquals(expectedSum,best.get());
    }

    @ParameterizedTest
    @CsvSource({
            "src/test/resources,puzzle-example-day1.txt,45000",
            "src/test/resources,puzzle-input-day1.txt,204837"
    })
    void findTheTop3(String dir,String file,int sumTop3) {
        ElfFactory elfFactory = new ElfFactory(dir,file);

        int top3sum = elfFactory.build().stream()
                .sorted(Comparator.comparing(Elf::sumCalories).reversed())
                .limit(3)
                .collect(Collectors.summingInt(Elf::sumCalories));

        assertEquals(sumTop3,top3sum);
    }
}
