package nl.tubby.aoc;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CliDirectoryParserTest {

    @ParameterizedTest
    @CsvSource({
            "puzzle-example-day7.txt,23,4"
    })
    void parse(String file,int expectedParseCount,int expectedRootCount) {
        var parser = new CliDirectoryParser();
        var slurper = new Slurper<>(parser::parse, Optional.empty(),Boolean::booleanValue);

        long parsed = slurper.count(Path.of(file));
        var root = parser.root;

        assertEquals(expectedParseCount,parsed);
        assertEquals(expectedRootCount,root.files.size());
    }

    @ParameterizedTest
    @CsvSource({
            "puzzle-example-day7.txt,48381165"
    })
    void getSize(String file,int expectedSize) {
        var parser = new CliDirectoryParser();
        var slurper = new Slurper<>(parser::parse);

        slurper.count(Path.of(file));

        assertEquals(expectedSize,parser.root.getSize());
    }
}