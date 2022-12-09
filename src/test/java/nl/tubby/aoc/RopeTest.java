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
    void moveHead(String start, String line,String end) {
        var rope = new Rope(Coordinates.parse(start));
        var move = Direction.parse(line);

        var touched  = rope.move(line).toList();

        assertEquals(move.getRight()+1,touched.size());
        assertEquals(Coordinates.parse(end),rope.head);
    }
}