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
            "puzzle-example-day6.txt,4,7",
            "puzzle-input-day6.txt,4,1093", // <-- answer part 1
            "puzzle-example-day6.txt,14,19",
            "puzzle-input-day6.txt,14,1093" // <-- answer part 2
    })
    void findFirstFromFile(String file,int startPacketLength,int expectedLocation) {
        var location = new DataStream(null,startPacketLength).first(Path.of(file));

        assertEquals(expectedLocation,location);
    }
}