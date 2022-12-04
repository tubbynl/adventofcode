package nl.tubby.aoc;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        var outcome = me.determineOutcome(opponent);

        assertEquals(expected,outcome);
    }

    @ParameterizedTest
    @CsvSource({
            "rock,paper,8",
            "paper,rock,1",
            "scissors,scissors,6"
    })
    void calculateScore(RockPaperScissors opponent, RockPaperScissors me, int expectedScore) {
        var round = new RockPaperScissorsRound(opponent,me,me.determineOutcome(opponent));
        var score = round.calculateScore();

        assertEquals(expectedScore,score);
    }

    @ParameterizedTest
    @CsvSource({
            "puzzle-example-day2.txt,15",
            "puzzle-input-day2.txt,10994"
    })
    void tournament(String filename, int expectedScore) {
        var totalScore = new RockPaperScissorsRoundSlurper()
                .slurp(Path.of("src/test/resources",filename))
                .collect(Collectors.summingInt(RockPaperScissorsRound::calculateScore));

        assertEquals(expectedScore,totalScore);
    }

    @ParameterizedTest
    @CsvSource({
            "puzzle-example-day2.txt,12",
            "puzzle-input-day2.txt,12526"
    })
    void tournament2(String filename, int expectedScore) {
        var totalScore = new RockPaperScissorsRoundSlurper2()
                .slurp(Path.of("src/test/resources",filename))
                .collect(Collectors.summingInt(RockPaperScissorsRound::calculateScore));

        assertEquals(expectedScore,totalScore);
    }
}