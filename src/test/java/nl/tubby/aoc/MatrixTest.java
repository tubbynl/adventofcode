package nl.tubby.aoc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * day 8
 */
class MatrixTest {
    Matrix sut = Matrix.slurp(Path.of("puzzle-example-day8.txt"));

    @Test
    void rowAndColum() {
        assertEquals(List.of(3,0,3,7,3),sut.row(new Coordinates(0,2)).toList());
        assertEquals(List.of(3,5,3,5,3),sut.col(new Coordinates(0,2)).toList());
        assertEquals(4,sut.value(new Coordinates(3,3)));
    }

    @Test
    void toMy() {
        Coordinates middle = new Coordinates(2,2);

        assertEquals(List.of(5,6),sut.toMyLeft(middle));
        assertEquals(List.of(5,3),sut.toMyTop(middle));
        assertEquals(List.of(3,2),sut.toMyRight(middle));
        assertEquals(List.of(5,3),sut.toMyBottom(middle));
    }

    @ParameterizedTest
    @CsvSource({
            "1,1,1,the top-left 5 is visible from the left",
            "1,2,2,the top-middle 5 is visible from the top",
            "1,3,0,the top-right 1 is not visible",
            "2,1,3,the left-middle 5 is visible from the right",
            "2,2,0,the center 3 is not visible",
            "2,3,3,right-middle 3 is visible from the right",
            "3,2,1,the bottom row, the middle 5 is visible",
            "3,1,0,the bottom row 3 is not visible",
            "3,3,4,the bottom row 4 is visible from the buttom",
    })
    void visibleFrom(int row,int col,int expectedVisibility,String message) {
        assertEquals(expectedVisibility,sut.visibleFrom(new Coordinates(row,col)),message);
    }

    @ParameterizedTest
    @CsvSource({
            "puzzle-example-day8.txt,22", // 22 because of the extra visible from bottom
            "puzzle-input-day8.txt,1703", // <-- solution part 1
            //"aoc_2022_day08_sparse.txt,27476" // <-- tweakers https://gathering.tweakers.net/forum/list_message/73727974#73727974
    })
    void assignment1(String file,int expectedCount) {
        var matrix = Matrix.slurp(Path.of(file));

        assertEquals(expectedCount,matrix.countVisibleTrees());
    }

    @ParameterizedTest
    @CsvSource({
            "puzzle-example-day8.txt,8",
            "puzzle-input-day8.txt,496650", // <-- solution part 2
            //"aoc_2022_day08_sparse.txt,2132894400" // <-- tweakers https://gathering.tweakers.net/forum/list_message/73727974#73727974
    })
    void assignment2(String file,int expectedHighestScenicScore) {
        var matrix = Matrix.slurp(Path.of(file));

        var highestScenicScore = matrix.stream().mapToInt(matrix::scenicScore).max().getAsInt();

        assertEquals(expectedHighestScenicScore,highestScenicScore);
    }
}