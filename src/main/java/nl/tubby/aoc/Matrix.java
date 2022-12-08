package nl.tubby.aoc;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

record Matrix(List<List<Integer>> values) {

    Matrix() {
        this(new ArrayList<>());
    }
    void addRow(Integer... row) {
        this.values().add(List.of(row));
    }

}
