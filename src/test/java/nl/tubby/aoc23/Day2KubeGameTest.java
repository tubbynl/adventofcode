package nl.tubby.aoc23;

import nl.tubby.aoc22.Path;
import nl.tubby.aoc22.Slurper;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class Day2KubeGameTest {
    @ParameterizedTest
    @CsvSource({
            "aoc-2023-day2-example.txt,5"
    })
    void parse(String file,int expectedSize) {
        var slurper = new Slurper<>(Day2KubeGame.Game::parse);

        var games = slurper.list(Path.of(file));

        assertEquals(expectedSize,games.size());
    }
}