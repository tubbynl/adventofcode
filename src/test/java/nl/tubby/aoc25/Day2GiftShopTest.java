package nl.tubby.aoc25;

import nl.tubby.Resource;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class Day2GiftShopTest {
    private final Day2GiftShop slurper = new Day2GiftShop();

    @ParameterizedTest
    @CsvSource({
            "day2-example.txt,1227775554",
            "day2-input1.txt,19605500130"
    })
    void parsePart1(Resource file, long sum) {
        var invalidIdSum = slurper.invalidIds(file,Day2GiftShop::idCheck1).sum();

        assertEquals(sum,invalidIdSum);
    }

    @ParameterizedTest
    @CsvSource({
            "day2-example.txt,4174379265",
            "day2-input1.txt,36862281418"
    })
    void parsePart2(Resource file, long sum) {
        var invalidIdSum = slurper.invalidIds(file,Day2GiftShop::idCheck2).sum();

        assertEquals(sum,invalidIdSum);
    }

    @ParameterizedTest
    @CsvSource({
            "998-1012,1",
            "222220-222224,1",
    })
    void idCheck1(String input, int invalidIdCount) {
        Day2GiftShop.IdRange range = Day2GiftShop.IdRange.parse(input);

        var invalidIds = range.stream()
                .filter(Day2GiftShop::idCheck1).boxed().toList();

        assertEquals(invalidIdCount,invalidIds.size());
    }

    @ParameterizedTest
    @CsvSource({
            "998-1012,2,999",
            "222220-222224,1,222222",
    })
    void idCheck2(String input, int invalidIdCount, long containsId) {
        Day2GiftShop.IdRange range = Day2GiftShop.IdRange.parse(input);

        var invalidIds = range.stream()
                .filter(Day2GiftShop::idCheck2).boxed().toList();

        assertEquals(invalidIdCount,invalidIds.size());
        assertTrue(invalidIds.contains(containsId));
    }
}