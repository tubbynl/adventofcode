package nl.tubby.aoc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {

    @Test
    void create() {
        Matrix matrix = new Matrix();
        matrix.addRow(1,2,3);
        matrix.addRow(3,1,2);
        matrix.addRow(2,3,1);


    }

}