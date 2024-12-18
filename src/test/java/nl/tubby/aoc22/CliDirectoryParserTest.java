package nl.tubby.aoc22;

import nl.tubby.Resource;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * day 7
 */
class CliDirectoryParserTest {

    @ParameterizedTest
    @CsvSource({
            "puzzle-example-day7.txt,4,48381165",
            "puzzle-input-day7.txt,173,41412830"
    })
    void parse(String file,int expectedDirCount,int expectedSize) {
        var root = CliDirectoryParser.parse(Resource.of(file));

        assertEquals(expectedDirCount,root.dirs().count());
        assertEquals(expectedSize,root.getSize());
    }

    @ParameterizedTest
    @CsvSource({
            "puzzle-example-day7.txt,95437",
            "puzzle-input-day7.txt,1749646"
    })
    void assignment1(String file,int expectedResult) {
        var root = CliDirectoryParser.parse(Resource.of(file));

        var result = root.dirs()
                .map(Dir::getSize)
                .mapToInt(Integer::intValue)
                .filter(size -> size<=100000)
                .sum();

        assertEquals(expectedResult,result);
    }

    @ParameterizedTest
    @CsvSource({
            "puzzle-example-day7.txt,70000000,30000000,24933642",
            "puzzle-input-day7.txt,70000000,30000000,1498966"
    })
    void assignment2(String file,int totalDiskSpace, int requiredDiskSpace,int sizeOfDirToDelete) {

        var root = CliDirectoryParser.parse(Resource.of(file));
        var spaceAvailable = totalDiskSpace-root.getSize();
        var extraSpaceRequired = requiredDiskSpace-spaceAvailable;

        var result = root.dirs()
                .mapToInt(Dir::getSize)
                .sorted()
                .filter(i -> i>=extraSpaceRequired)
                .findFirst().getAsInt();

        assertEquals(sizeOfDirToDelete,result);
    }
}