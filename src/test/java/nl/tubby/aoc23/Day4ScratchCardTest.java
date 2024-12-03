package nl.tubby.aoc23;

import nl.tubby.Resource;
import nl.tubby.Slurper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class Day4ScratchCardTest {

    @CsvSource({
            "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53,1,8",
            "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19,2,2",
            "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1,3,2",
            "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83,4,1",
            "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36,5,0",
            "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11,6,0"
    })
    @ParameterizedTest
    void parseScore(String rawCard,int nr,int score) {
        var card = Day4ScratchCard.ScratchCard.parse(rawCard);

        assertEquals(nr,card.nr());
        assertEquals(score,card.score());
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

        var sumPoints = slurper.sum(Resource.of(file),Day4ScratchCard.ScratchCard::score);

        assertEquals(points,sumPoints);
    }

    @ParameterizedTest
    @CsvSource({
            "aoc-2023-day4-example.txt,30",
            "aoc-2023-day4-input.txt,11827296"
    })
    void puzzlePart2(String file,long cardCount) {
        var slurper = new Slurper<>(Day4ScratchCard.ScratchCard::parse);

        var cards = slurper.list(Resource.of(file));

        var allCards = cards.stream().mapToInt(c -> c.cardCount(cards)).sum();
        assertEquals(cardCount,allCards);
    }
}