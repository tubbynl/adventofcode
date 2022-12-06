package nl.tubby.aoc;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class DataStreamTest {

    @ParameterizedTest
    @CsvSource({
            "mjqjpqmgbljsphdztnvjfqwrcgsmlb,7",
            "bvwbjplbgvbhsrlpgdmjqwftvncz,5",
            "nppdvjthqldpwncqszvftbrmjlhg,6",
            "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg,10",
            "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw,11"
    })
    void findFirst(String input,int expectedLocation) {
        var location = new DataStream(input).first(null);

        assertEquals(expectedLocation,location);
    }

    @ParameterizedTest
    @CsvSource({
            "puzzle-example-day6.txt,7",
            "puzzle-input-day6.txt,1093"
    })
    void findFirstFromFile(String file,int expectedLocation) {
        var location = new DataStream(null).first(Path.of(file));

        assertEquals(expectedLocation,location);
    }
}