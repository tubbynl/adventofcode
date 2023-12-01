package nl.tubby.aoc23;

import nl.tubby.aoc22.Path;
import nl.tubby.aoc22.Slurper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day1CalibatorTest {

    @ParameterizedTest
    @CsvSource({
            "aoc-2023-day1-example.txt,142",
            "aoc-2023-day1-input.txt,54968"// <-- solution part 1
    })
    void parseAndSum(String file,int expectedCalibrationSum) {
        var slurper = new Slurper<>(Day1Calibator::parseDigits);

        assertEquals(expectedCalibrationSum,slurper.sum(Path.of(file),Integer::intValue));
    }

    @ParameterizedTest
    @CsvSource({
            "aoc-2023-day1-example.txt,142",
            "aoc-2023-day1-input.txt,54094"// <-- solution part 2
    })
    void parseLettersAndSum(String file,int expectedCalibrationSum) {
        var slurper = new Slurper<>(Day1Calibator::parseLetters);

        assertEquals(expectedCalibrationSum,slurper.sum(Path.of(file),Integer::intValue));
    }

    @ParameterizedTest
    @CsvSource({
            "1abc2,12",
            "pqr3stu8vwx,38",
            "a1b2c3d4e5f,15",
            "treb7uchet,77",
            "two1nine,29",
            "eightwothree,83",
            "abcone2threexyz,13",
            "xtwone3four,24",
            "4nineeightseven2,42",
            "zoneight234,14",
            "7pqrstsixteen,76",
            "seventhree2pqrstsi2twone,71"
    })
    void parseLetters(String input,int expectedCalibration) {

        assertEquals(expectedCalibration,Day1Calibator.parseLetters(input));
    }

    @Test
    void testSubstringStream() {
        var forwards = Day1Calibator.substringStream("tubby",s -> s.substring(1)).toList();
        assertEquals(List.of("tubby","ubby","bby","by","y"),forwards);

        var backwards = Day1Calibator.substringStream("tubby",s -> s.substring(0,s.length()-1)).toList();
        assertEquals(List.of("tubby","tubb","tub","tu","t"),backwards);
    }
}