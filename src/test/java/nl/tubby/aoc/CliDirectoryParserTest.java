package nl.tubby.aoc;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CliDirectoryParserTest {

    @ParameterizedTest
    @CsvSource({
            "puzzle-example-day7.txt,4,48381165",
            "puzzle-input-day7.txt,10,41412830"
    })
    void parse(String file,int expectedRootCount,int expectedSize) {
        var root = CliDirectoryParser.parse(Path.of(file));

        assertEquals(expectedRootCount,root.files.size());
        assertEquals(expectedSize,root.getSize());
    }

    @ParameterizedTest
    @CsvSource({
            "puzzle-example-day7.txt,95437",
            "puzzle-input-day7.txt,1749646"
    })
    void assignment1(String file,int expectedResult) {
        var root = CliDirectoryParser.parse(Path.of(file));

        var result = root.dirs()
                .map(Dir::getSize)
                .mapToInt(Integer::intValue)
                .filter(size -> size<=100000)
                .sum();

        assertEquals(expectedResult,result);
    }
}