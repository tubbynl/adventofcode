package nl.tubby.aoc;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RockPaperScissorsTest {

    @ParameterizedTest
    @CsvSource({
            "A,rock",
            "B,paper",
            "C,scissor",
            "X,rock",
            "Y,paper",
            "Z,scissor"
    })
    void testIfParsable(String value,RockPaperScissors expected) {
        assertEquals(Optional.of(expected),RockPaperScissors.parse(value));
    }
}