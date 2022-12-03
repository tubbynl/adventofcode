package nl.tubby.aoc;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class RockPaperScissorsTest {

    @ParameterizedTest
    @CsvSource({
            "A,rock",
            "B,paper",
            "C,paper",
            "X,rock",
            "Y,scissor",
            "Z,scissor"
    })
    void testIfParsable(String value,RockPaperScissors expected) {
        assertEquals(expected,RockPaperScissors.parse(value));
    }
}