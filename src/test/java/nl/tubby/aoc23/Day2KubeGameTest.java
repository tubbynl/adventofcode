package nl.tubby.aoc23;

import nl.tubby.aoc22.Path;
import nl.tubby.aoc22.Slurper;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class Day2KubeGameTest {

    @ParameterizedTest
    @CsvSource(value = {
            "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green|1|4|2|6|48",
            "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue|2|1|3|4|12",
            "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red|3|20|13|6|1560",
            "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red|4|14|3|15|630",
            "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green|5|6|3|2|36",
            "Game 41: 7 red, 10 green; 10 red, 6 green; 9 red, 7 green, 1 blue; 3 red, 1 blue|41|10|10|1|100",
            "Game 82: 5 blue, 3 red, 3 green; 5 red; 2 red, 3 green, 8 blue|82|5|3|8|120",
            "Game 65: 7 red, 7 blue; 3 blue, 1 red, 1 green; 3 red, 8 blue|65|7|1|8|56",
            "Game 85: 3 green, 2 red; 5 green, 4 blue; 5 green, 8 red, 3 blue|85|8|5|4|160",
            "Game 67: 1 red; 2 blue, 2 green, 1 red; 6 green, 1 blue|67|1|6|2|12"
    },delimiterString = "|")
    void parseGame(String rawString,int id,int red, int green,int blue,int power) {
        var game = Day2KubeGame.Game.parse(rawString);

        assertEquals(id,game.id());
        assertEquals(red,game.red(),"red");
        assertEquals(green,game.green(),"green");
        assertEquals(blue,game.blue(),"blue");
        assertEquals(power,game.power());
    }

    @ParameterizedTest
    @CsvSource(value = {
            "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green|6|18",
            "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue|7|13",
            "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red|8|62",
            "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red|8|51",
            "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green|6|15",
            "Game 41: 7 red, 10 green; 10 red, 6 green; 9 red, 7 green, 1 blue; 3 red, 1 blue|9|54",
            "Game 82: 5 blue, 3 red, 3 green; 5 red; 2 red, 3 green, 8 blue|7|29",
            "Game 65: 7 red, 7 blue; 3 blue, 1 red, 1 green; 3 red, 8 blue|7|30",
            "Game 85: 3 green, 2 red; 5 green, 4 blue; 5 green, 8 red, 3 blue|7|30",
            "Game 67: 1 red; 2 blue, 2 green, 1 red; 6 green, 1 blue|6|13",
            "Game 18: 8 green, 1 red, 2 blue; 4 green, 4 red, 1 blue; 6 blue, 2 red|8|28",
            "Game 19: 3 green, 9 blue; 4 blue, 10 red; 6 red, 3 green, 3 blue; 6 red, 4 green, 9 blue|10|57"
    },delimiterString = "|")
    void parseKubeCount(String rawString,int expectedSize, int expectedSum) {
        var counts = Day2KubeGame.KubeCount.parse(rawString).toList();

        assertEquals(expectedSize,counts.size());
        assertEquals(expectedSum,counts.stream().mapToInt(Day2KubeGame.KubeCount::amount).sum());

        assertEquals(Set.of("red","green","blue"),counts.stream().map(Day2KubeGame.KubeCount::color).collect(Collectors.toSet()));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1,1,1,true",
            "12,13,14,true",
            "12,14,14,false",
            "13,13,13,false",
            "100,100,100,false"
    })
    void parseGame(int red, int green,int blue, boolean possible) {
        var game = new Day2KubeGame.Game(1337,red,green,blue);

        assertEquals(possible,game.isPossible());
    }

    @ParameterizedTest
    @CsvSource({
            "aoc-2023-day2-example.txt,5",
            "aoc-2023-day2-input.txt,100"
    })
    void parse(String file,int expectedSize) {
        var slurper = new Slurper<>(Day2KubeGame.Game::parse);

        var games = slurper.list(Path.of(file));

        assertEquals(expectedSize,games.size());

        // only 12 red cubes, 13 green cubes, and 14 blue cubes
        var gamesPossible = games.stream()
                .filter(Day2KubeGame.Game::isPossible)
                .toList();//10,18,27,67,82,85

        assertFalse(gamesPossible.isEmpty());
    }

    @ParameterizedTest
    @CsvSource({
            "aoc-2023-day2-example.txt,8",
            "aoc-2023-day2-input.txt,3059"// answer part 1
    })
    void resultForPuzzle1(String file, int sumIds) {
        var slurper = new Slurper<>(Day2KubeGame.Game::parse, Day2KubeGame.Game::isPossible);

        assertEquals(sumIds,slurper.sum(Path.of(file),Day2KubeGame.Game::id));
    }

    @ParameterizedTest
    @CsvSource({
            "aoc-2023-day2-example.txt,2286",
            "aoc-2023-day2-input.txt,3059"// answer part 1
    })
    void resultForPuzzle2(String file, int sumPower) {
        var slurper = new Slurper<>(Day2KubeGame.Game::parse);

        assertEquals(sumPower,slurper.sum(Path.of(file),Day2KubeGame.Game::power));
    }
}