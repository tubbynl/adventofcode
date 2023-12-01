package nl.tubby.aoc22;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * day 1
 */
public class ElfCaloriesCollectorTest {

    @Test
    void build() {
        var slurper = new Slurper<>(new ElfCaloriesCollector()::collectOrParse);
        var elves = slurper.list(Path.of("puzzle-example-day1.txt"));

        assertEquals(5,elves.size());
        assertEquals(6000,elves.get(0));
        assertEquals(4000,elves.get(1));
    }

    @ParameterizedTest
    @CsvSource({
            "puzzle-example-day1.txt,24000",
            "puzzle-input-day1.txt,68442",// <-- solution part 1
            //"aoc_2022_day01_large_input.txt,184028272" //https://gathering.tweakers.net/forum/list_message/73652172#73652172
    })
    void findTheBest(String file,int expectedSum) {
        var slurper = new Slurper<>(new ElfCaloriesCollector()::collectOrParse);
        int best = slurper.max(Path.of(file),Integer::intValue);

        assertEquals(expectedSum,best);
    }

    @ParameterizedTest
    @CsvSource({
            "puzzle-example-day1.txt,45000",
            "puzzle-input-day1.txt,204837",// <-- solution part 2
            //"aoc_2022_day01_large_input.txt,549010145"
    })
    void findTheTop3(String file,int sumTop3) {
        var slurper = new Slurper<>(new ElfCaloriesCollector()::collectOrParse);
        int top3sum = slurper.slurp(Path.of(file))
                .sorted(Collections.reverseOrder())
                .mapToInt(Integer::intValue)
                .limit(3)
                .sum();

        assertEquals(sumTop3,top3sum);
    }
}
