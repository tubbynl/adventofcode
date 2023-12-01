package nl.tubby.aoc23;

import nl.tubby.aoc22.Path;
import nl.tubby.aoc22.Slurper;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class Day1CalibatorTest {

    @ParameterizedTest
    @CsvSource({
            "aoc-2023-day1-example.txt,142",
            "puzzle-input-day4.txt,511"// <-- solution part 1
    })
    void extract(String file,int expectedCalibrationSum) {
        var slurper = new Slurper<>(Day1Calibator::parse);

        assertEquals(expectedCalibrationSum,slurper.sum(Path.of(file),Integer::intValue));
    }
}