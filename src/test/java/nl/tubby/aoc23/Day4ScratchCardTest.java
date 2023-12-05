package nl.tubby.aoc23;

import nl.tubby.aoc22.Path;
import nl.tubby.aoc22.Slurper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class Day4ScratchCardTest {

    @CsvSource({
            "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53,8",
            "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19,2",
            "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1,2",
            "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83,1",
            "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36,0",
            "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11,0"
    })
    @ParameterizedTest
    void parseScore(String card,int score) {
        assertEquals(score,Day4ScratchCard.ScratchCard.parse(card).getScore());
    }

    @Test
    void getPoints() {
        assertEquals(0,Day4ScratchCard.getPoints(0));
        assertEquals(1,Day4ScratchCard.getPoints(1));
        assertEquals(2,Day4ScratchCard.getPoints(2));
        assertEquals(4,Day4ScratchCard.getPoints(3));
        assertEquals(8,Day4ScratchCard.getPoints(4));
        assertEquals(16,Day4ScratchCard.getPoints(5));
    }

    @ParameterizedTest
    @CsvSource({
            "aoc-2023-day4-example.txt,13",
            "aoc-2023-day4-input.txt,21568"
    })
    void puzzlePart1(String file,int points) {
        var slurper = new Slurper<>(Day4ScratchCard.ScratchCard::parse);

        var sumPoints = slurper.sum(Path.of(file),Day4ScratchCard.ScratchCard::getScore);

        assertEquals(points,sumPoints);
    }
}