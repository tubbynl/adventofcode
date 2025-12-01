package nl.tubby.aoc25;

import nl.tubby.Resource;
import nl.tubby.Slurper;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class Day1PwDialTest {


    @ParameterizedTest
    @CsvSource({
            "day1-example.txt,10,32,3,6",
            "day1-input1.txt,4035,37,964,5872"
    })
    void totalDistance(Resource file, int count, int endpos, int pw, int pw2) {
        var dialer = new Day1PwDial(50);

        var slurper = new Slurper<>(dialer::turn);

        var dials = slurper.slurp(file).count();

        assertEquals(count, dials);
        assertEquals(endpos,dialer.getPos());
        assertEquals(pw,dialer.getZeros());
        assertEquals(pw2,dialer.getZeros()+dialer.getZeroPassed());
    }
}