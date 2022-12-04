package nl.tubby.aoc;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.nio.file.Path;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RockPaperTournamentTest {

    @ParameterizedTest
    @CsvSource({
            "puzzle-example-day2.txt,15",
            "puzzle-input-day2.txt,10994"
    })
    void tournament(String filename, int expectedScore) {
        var slurper = new RockPaperScissorsRoundSlurper();

        var rounds = slurper.slurp(Path.of("src/test/resources",filename));

        var totalScore = rounds.collect(Collectors.summingInt(RockPaperScissorsRound::calculateScore));

        assertEquals(expectedScore,totalScore);
    }

    @ParameterizedTest
    @CsvSource({
            "puzzle-example-day2.txt,12",
            "puzzle-input-day2.txt,12526"
    })
    void tournament2(String filename, int expectedScore) {
        var slurper = new RockPaperScissorsRoundSlurper2();

        var rounds = slurper.slurp(Path.of("src/test/resources",filename));

        var totalScore = rounds.collect(Collectors.summingInt(RockPaperScissorsRound::calculateScore));

        assertEquals(expectedScore,totalScore);
    }
}
