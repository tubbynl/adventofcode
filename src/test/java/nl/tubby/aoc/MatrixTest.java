package nl.tubby.aoc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {

    @Test
    void rowAndColum() {
        var matrix = Matrix.slurp(Path.of("puzzle-example-day8.txt"));

        assertEquals(List.of(3,0,3,7,3),matrix.row(0).toList());
        assertEquals(List.of(3,5,3,5,3),matrix.col(2).toList());
        assertEquals(4,matrix.value(3,3));
    }

    @Test
    void isVisible() {
        var matrix = Matrix.slurp(Path.of("puzzle-example-day8.txt"));

        assertTrue(matrix.isVisible(1,1));// the top-left 5 is visible
        assertTrue(matrix.isVisible(1,2));// the top-middle 5 is visible
        assertFalse(matrix.isVisible(1,2));// the top-right 1 is not visible
        assertTrue(matrix.isVisible(2,1));// the left-middle 5 is visible
        assertFalse(matrix.isVisible(2,2));// the center 3 is not visible
        assertTrue(matrix.isVisible(2,2));// right-middle 3 is visible
        assertTrue(matrix.isVisible(3,2));// the bottom row, the middle 5 is visible
        assertFalse(matrix.isVisible(3,1));// the bottom row, 3 is not visible
        assertFalse(matrix.isVisible(3,3));// the bottom row, 4 is not visible
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