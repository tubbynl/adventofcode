package nl.tubby.aoc;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class RockPaperScissorsRoundTest {

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
    void outcome(RockPaperScissors opponent, RockPaperScissors me, RoundState expected) {
        var round = new RockPaperScissorsRound(opponent,me);
        var outcome = round.outcome();

        assertEquals(expected,outcome);
    }

    @ParameterizedTest
    @CsvSource({
            "rock,paper,8",
            "paper,rock,1",
            "scissors,scissors,6"
    })
    void calculateScore(RockPaperScissors opponent, RockPaperScissors me, int expectedScore) {
        var round = new RockPaperScissorsRound(opponent,me);
        var score = round.calculateScore();

        assertEquals(expectedScore,score);
    }
}