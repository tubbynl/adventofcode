package nl.tubby.aoc;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class RopeTest {

    @ParameterizedTest
    @CsvSource({
            "0.0,R 1,1.0",
            "0.0,D 1,0.1",
            "1.0,L 1,0.0",
            "0.1,U 1,0.0",
            "0.0,R 5,5.0",
            "0.0,D 3,0.3"
    })
    void moveHead(String start, String line, String end) {
        var rope = new Rope(1,Coordinates.parse(start));
        var move = Movement.parse(line);

        var touched = rope.moveHead(move).toList();

        assertEquals(move.steps(), touched.size());
        assertEquals(Coordinates.parse(end), rope.head);
    }

    @ParameterizedTest
    @CsvSource({
            "puzzle-example-day9.txt,13",
            "puzzle-input-day9.txt,6197"
    })
    void assignment1(String file, long expectedTailLocations) {
        var start = Coordinates.parse("0.4");
        var result = Rope.countTailPositions(start,Path.of(file),1);

        assertEquals(expectedTailLocations, result);
    }

    @ParameterizedTest
    @CsvSource({
            "puzzle-example-day9.txt,0.4,1",
            //"puzzle-example-day9-2.txt,11.15,36", FIXME: :)
            //"puzzle-input-day9.txt,11.15,6197"
    })
    void assignment2(String file, String startStr,long expectedTailLocations) {
        var start = Coordinates.parse(startStr);
        var result = Rope.countTailPositions(start,Path.of(file),9);

        assertEquals(expectedTailLocations, result);
    }
}