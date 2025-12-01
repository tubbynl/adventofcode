package nl.tubby.aoc25;

import nl.tubby.Resource;
import nl.tubby.Slurper;
import nl.tubby.aoc24.Day1LocationLinker;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class Day1PwDialTest {


    @ParameterizedTest
    @CsvSource({
            "day1-example.txt,10,32"
    })
    void totalDistance(Resource file, int count, int endpos) {
        var dialer = new Day1PwDial(50);

        var slurper = new Slurper<>(dialer::turn);

        var dials = slurper.slurp(file).count();

        assertEquals(count, dials);
        assertEquals(endpos,dialer.getPos());
    }
}