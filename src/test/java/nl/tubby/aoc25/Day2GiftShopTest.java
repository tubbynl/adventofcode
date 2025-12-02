package nl.tubby.aoc25;

import nl.tubby.Resource;
import nl.tubby.Slurper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.function.Function;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class Day2GiftShopTest {
    private final Day2GiftShop slurper = new Day2GiftShop();

    @ParameterizedTest
    @CsvSource({
            "day2-example.txt,8,1227775554",
            "day2-input1.txt,734,19605500130"
    })
    void parsePart1(Resource file, int invalidIdCount, long sum) {
        var invalidIds = slurper.invalidIds(file,Day2GiftShop.IdRange::idCheck1)
                .toList();

        assertEquals(invalidIdCount,invalidIds.size());

        assertEquals(sum,invalidIds.stream().mapToLong(Long::longValue).sum());
    }

    @ParameterizedTest
    @CsvSource({
            "day2-example.txt,13,4174379265",
            "day2-input1.txt,802,36862281418"
    })
    void parsePart2(Resource file, int invalidIdCount, long sum) {
        var invalidIds = slurper.invalidIds(file,Day2GiftShop.IdRange::idCheck2)
                .toList();

        assertEquals(invalidIdCount,invalidIds.size());

        assertEquals(sum,invalidIds.stream().mapToLong(Long::longValue).sum());
    }

    @ParameterizedTest
    @CsvSource({
            "998-1012,1",
            "222220-222224,1",
    })
    void idCheck1(String input, int invalidIdCount) {
        Day2GiftShop.IdRange range = Day2GiftShop.IdRange.parse(input);

        var invalidIds = range.invalidIds(Day2GiftShop.IdRange::idCheck1).toList();

        assertEquals(invalidIdCount,invalidIds.size());
    }

    @ParameterizedTest
    @CsvSource({
            "998-1012,2",
            "222220-222224,1",
    })
    void idCheck2(String input, int invalidIdCount) {
        Day2GiftShop.IdRange range = Day2GiftShop.IdRange.parse(input);

        var invalidIds = range.invalidIds(Day2GiftShop.IdRange::idCheck2).toList();

        assertEquals(invalidIdCount,invalidIds.size());
    }
}