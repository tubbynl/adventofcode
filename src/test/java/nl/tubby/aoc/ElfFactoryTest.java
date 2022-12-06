package nl.tubby.aoc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * day 1
 */
public class ElfFactoryTest {

    @Test
    void build() {
        var elves = new ElfFactory()
                .list(Path.of("puzzle-example-day1.txt"));

        assertEquals(5,elves.size());
        var first = elves.get(0);
        assertEquals(6000,first);

        var second = elves.get(1);;
        assertEquals(4000,second);
    }

    @ParameterizedTest
    @CsvSource({
            "puzzle-example-day1.txt,24000",
            "puzzle-input-day1.txt,68442",// <-- solution part 1
            //"aoc_2022_day01_large_input.txt,184028272" //https://gathering.tweakers.net/forum/list_message/73652172#73652172
    })
    void findTheBest(String file,int expectedSum) {
        int best = new ElfFactory()
                .max(Path.of(file),Integer::intValue);

        assertEquals(expectedSum,best);
    }

    @ParameterizedTest
    @CsvSource({
            "puzzle-example-day1.txt,45000",
            "puzzle-input-day1.txt,204837",// <-- solution part 2
            //"aoc_2022_day01_large_input.txt,549010145"
    })
    void findTheTop3(String file,int sumTop3) {
        int top3sum = new ElfFactory()
                .slurp(Path.of(file))
                .sorted(Collections.reverseOrder())
                .mapToInt(Integer::intValue)
                .limit(3)
                .sum();

        assertEquals(sumTop3,top3sum);
    }
}
