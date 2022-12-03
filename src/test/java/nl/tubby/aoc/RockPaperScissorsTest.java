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
            "C,scissors",
            "X,rock",
            "Y,paper",
            "Z,scissors"
    })
    void testIfParsable(String value,RockPaperScissors expected) {
        assertEquals(Optional.of(expected),RockPaperScissors.parse(value));
    }

    @ParameterizedTest
    @CsvSource({
            "rock,scissors,loss",//Rock defeats Scissors
            "scissors,rock,win",
            "scissors,paper,loss",//Scissors defeats Paper
            "paper,scissors,win",
            "paper,rock,loss",//Paper defeats Rock
            "rock,paper,win",
            "rock,rock,tie",//If both players choose the same shape, the round instead ends in a draw
            "paper,paper,tie",
            "scissors,scissors,tie",
    })
    void determine(RockPaperScissors opponent, RockPaperScissors me, RoundState expected) {
        var outcome = me.determine(opponent);

        assertEquals(expected,outcome);
    }
}