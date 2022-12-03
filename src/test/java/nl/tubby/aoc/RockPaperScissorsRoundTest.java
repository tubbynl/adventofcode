package nl.tubby.aoc;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class RockPaperScissorsRoundTest {

    @ParameterizedTest
    @CsvSource({
            "rock,paper,8",
            "paper,rock,1",
            "scissors,scissors,6"
    })
    void calculateScore(RockPaperScissors opponent, RockPaperScissors me, int expectedScore) {
        var round = new RockPaperScissorsRound(opponent,me,RoundState.determine(opponent, me));
        var score = round.calculateScore();

        assertEquals(expectedScore,score);
    }
}