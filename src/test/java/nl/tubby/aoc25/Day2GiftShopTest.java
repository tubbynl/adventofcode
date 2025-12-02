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

    @ParameterizedTest
    @CsvSource({
            "day2-example.txt,11,8,1227775554",
            "day2-input1.txt,33,734,19605500130"
    })
    void parsePart1(Resource file, int rangeCount, int invalidIdCount, long sum) {
        var slurper = new Slurper<>(Day2GiftShop::parse);

        var idRanges = slurper.slurp(file)
                .flatMap(Function.identity())
                .toList();

        assertEquals(rangeCount,idRanges.size());

        var invalidIds = idRanges.stream()
                .flatMap(range -> range.invalidIds(Day2GiftShop.IdRange::idCheck1))
                .toList();

        assertEquals(invalidIdCount,invalidIds.size());

        assertEquals(sum,invalidIds.stream().mapToLong(Long::longValue).sum());
    }

    @ParameterizedTest
    @CsvSource({
            "day2-example.txt,11,13,4174379265",
            "day2-input1.txt,33,802,36862281418"
    })
    void parsePart2(Resource file, int rangeCount, int invalidIdCount, long sum) {
        var slurper = new Slurper<>(Day2GiftShop::parse);

        var idRanges = slurper.slurp(file)
                .flatMap(Function.identity())
                .toList();

        assertEquals(rangeCount,idRanges.size());

        var invalidIds = idRanges.stream()
                .flatMap(range -> range.invalidIds(Day2GiftShop.IdRange::idCheck2))
                .toList();

        assertEquals(invalidIdCount,invalidIds.size());

        assertEquals(sum,invalidIds.stream().mapToLong(Long::longValue).sum());
    }
}