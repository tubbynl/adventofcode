package nl.tubby.aoc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {
    Matrix sut = Matrix.slurp(Path.of("puzzle-example-day8.txt"));

    @Test
    void rowAndColum() {
        assertEquals(List.of(3,0,3,7,3),sut.row(0).toList());
        assertEquals(List.of(3,5,3,5,3),sut.col(2).toList());
        assertEquals(4,sut.value(3,3));
    }

    @ParameterizedTest
    @CsvSource({
            "1,1,1,the top-left 5 is visible from the left",
            "1,2,2,the top-middle 5 is visible from the top",
            "1,3,0,the the top-right 1 is not visible",
            "2,1,3,the the left-middle 5 is visible from the right",
            "2,2,0,the center 3 is not visible",
            "2,3,3,right-middle 3 is visible from the right",
            "3,2,1,the bottom row, the middle 5 is visible",
            "3,1,0,the bottom row 3 is not visible",
            "3,3,0,the bottom row 4 is not visible",
    })
    void isVisible(int row,int col,int expectedVisibility,String message) {
        assertEquals(expectedVisibility,sut.isVisible(row,col),message);
    }

    @ParameterizedTest
    @CsvSource({
            "puzzle-example-day8.txt,21"
    })
    void assignment1(String file,int expectedCount) {
        var matrix = Matrix.slurp(Path.of(file));

        assertEquals(expectedCount,matrix.countVisibleTrees());
    }
}