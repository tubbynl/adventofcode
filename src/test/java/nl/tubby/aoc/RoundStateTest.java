package nl.tubby.aoc;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class RoundStateTest {

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
        var outcome = RoundState.determine(opponent,me);

        assertEquals(expected,outcome);
    }
}

