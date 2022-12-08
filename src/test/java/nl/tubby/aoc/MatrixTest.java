package nl.tubby.aoc;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {

    @Test
    void rowAndColum() {
        Matrix matrix = new Matrix();
        matrix.addRow(1,2,3);
        matrix.addRow(3,1,2);
        matrix.addRow(2,3,1);

        IntStream.of(0,1,2).forEach(i -> {
            assertEquals(3,matrix.col(i).max().getAsInt());
            assertEquals(3,matrix.row(i).max().getAsInt());
        });
    }
}