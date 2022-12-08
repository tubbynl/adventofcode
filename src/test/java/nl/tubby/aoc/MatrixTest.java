package nl.tubby.aoc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {

    @Test
    void rowAndColum() {
        Matrix matrix = new Matrix();
        matrix.add(List.of(1,2,3));
        matrix.add(List.of(3,1,2));
        matrix.add(List.of(2,3,1));

        IntStream.of(0,1,2).forEach(i -> {
            assertEquals(3,matrix.col(i).max().getAsInt());
            assertEquals(3,matrix.row(i).max().getAsInt());
        });
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