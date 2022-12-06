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
        var location = new DataStream(input,4).first(null);

        assertEquals(expectedLocation,location);
    }

    @ParameterizedTest
    @CsvSource({
            "mjqjpqmgbljsphdztnvjfqwrcgsmlb,19",
            "bvwbjplbgvbhsrlpgdmjqwftvncz,23",
            "nppdvjthqldpwncqszvftbrmjlhg,23",
            "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg,29",
            "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw,26"
    })
    void findFirst14(String input,int expectedLocation) {
        var location = new DataStream(input,14).first(null);

        assertEquals(expectedLocation,location);
    }

    @ParameterizedTest
    @CsvSource({
            "puzzle-example-day6.txt,7",
            "puzzle-input-day6.txt,1093" // <-- answer part 1
    })
    void findFirstFromFile(String file,int expectedLocation) {
        var location = new DataStream(null,4).first(Path.of(file));

        assertEquals(expectedLocation,location);
    }
}