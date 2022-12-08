package nl.tubby.aoc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {

    @Test
    void rowAndColum() {
        var matrix = new Matrix();
        var slurper = new Slurper<>(matrix::add);
        slurper.slurp(Path.of("puzzle-example-day8.txt")).count();

        assertEquals(List.of(3,0,3,7,3),matrix.row(0).boxed().toList());
        assertEquals(List.of(3,5,3,5,3),matrix.col(2).boxed().toList());
        assertEquals(4,matrix.value(3,3));
    }

    @ParameterizedTest
    @CsvSource({
            "puzzle-example-day8.txt"
    })
    void assignment1(String file) {
        var matrix = new Matrix();
        var slurper = new Slurper<>(matrix::add);
        slurper.slurp(Path.of(file));

        //assertEquals(7,matrix.row(0).max().getAsInt());
    }
}