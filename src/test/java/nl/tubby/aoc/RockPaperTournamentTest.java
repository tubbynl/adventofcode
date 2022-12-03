package nl.tubby.aoc;

import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RockPaperTournamentTest {

    @Test
    void tournament() {
        var slurper = new RockPaperScissorsRoundSlurper("src/test/resources","puzzle-example-day2.txt");

        var rounds = slurper.slurp().collect(Collectors.toList());

        var totalScore = rounds.stream().collect(Collectors.summingInt(RockPaperScissorsRound::calculateScore));

        assertEquals(15,totalScore);
    }
}
