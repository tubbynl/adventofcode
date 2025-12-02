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
            "day2-example.txt,11,8,1227775554"
    })
    void parse(Resource file, int rangeCount, int invalidIdCount, int sum) {
        var slurper = new Slurper<>(Day2GiftShop::parse);

        var idRanges = slurper.slurp(file)
                .flatMap(Function.identity())
                .toList();

        assertEquals(rangeCount,idRanges.size());

        var invalidIds = idRanges.stream().flatMap(Day2GiftShop.IdRange::invalidIds).toList();

        assertEquals(invalidIdCount,invalidIds.size());

        assertEquals(sum,invalidIds.stream().mapToInt(Integer::intValue).sum());
    }
}