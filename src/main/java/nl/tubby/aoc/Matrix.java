package nl.tubby.aoc;

import java.awt.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

record Matrix(List<List<Integer>> values) {

    Matrix() {
        this(new ArrayList<>());
    }

    static Matrix slurp(Path path) {
        var matrix = new Matrix();
        var slurper = new Slurper<>(matrix::add);
        slurper.slurp(path).count();
        return matrix;
    }

    Boolean add(String line) {
        return add(line.chars()
                .filter(Character::isDigit)
                .mapToObj(Character::getNumericValue)
                .toList());
    }
    Boolean add(List<Integer> row) {
        this.values().add(row);
        return Boolean.TRUE;
    }

    int value(int row,int col) {
        if(values().size()<=row) {
            return 0;
        }
        return getAt(values().get(row),col);
    }

    static Integer getAt(List<Integer> list,int index) {
        return list.size()<=index?0:list.get(index);
    }

    Stream<Integer> row(int row) {
        return values().size()<=row? Stream.empty():values().get(row).stream();
    }

    Stream<Integer> col(int col) {
        return values().stream().map(row -> getAt(row,col));
    }

    Dimension size() {
        return new Dimension(
                Long.valueOf(row(0).count()).intValue(),
                Long.valueOf(col(0).count()).intValue());
    }

    boolean isVisible(int row,int col) {
        int myValue = value(row,col);
        List<Integer> myRow = row(row).toList();
        List<Integer> myCol = col(col).toList();
        if(IntStream.range(0,col).map(myRow::get).filter(v -> v>=myValue).findFirst().isPresent()) {
            return false;
        }
        return true;
    }

    int countVisibleTrees() {
        var size = size();
        int count = 0;
        for(int row=0;row<size.height;row++) {
            for(int col=0;col<size.width;col++) {
                if(isVisible(row,col)) {
                    count++;
                }
            }
        }
        return count;
    }
}
