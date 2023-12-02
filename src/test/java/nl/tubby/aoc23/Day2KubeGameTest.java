package nl.tubby.aoc23;

import nl.tubby.aoc22.Path;
import nl.tubby.aoc22.Slurper;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class Day2KubeGameTest {

    @ParameterizedTest
    @CsvSource(value = {
            "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green|5|4|9",
            "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue|1|6|6",
            "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red|25|26|11",
            "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red|23|7|21",
            "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green|7|5|3",
    },delimiterString = "|")
    void parseGame(String rawString,int red, int green,int blue) {
        var game = Day2KubeGame.Game.parse(rawString);

        assertEquals(red,game.red(),"red");
        assertEquals(green,game.green(),"green");
        assertEquals(blue,game.blue(),"blue");
    }

    @ParameterizedTest
    @CsvSource({
            "aoc-2023-day2-example.txt,5,8",
            "aoc-2023-day2-input.txt,100,289"// not the right answer yet (sum of ids is too low; check for parsing errors in full set)
    })
    void parse(String file,int expectedSize, int sumIds) {
        var slurper = new Slurper<>(Day2KubeGame.Game::parse);

        var games = slurper.list(Path.of(file));

        assertEquals(expectedSize,games.size());

        // only 12 red cubes, 13 green cubes, and 14 blue cubes
        var gamesPossible = games.stream()
                .filter(game -> game.red()<=12)
                .filter(game -> game.green()<=13)
                .filter(game -> game.blue()<=14)
                .toList();

        assertEquals(sumIds,gamesPossible.stream()
                .mapToInt(Day2KubeGame.Game::id)
                .sum());
    }
}