package nl.tubby.aoc;

import java.awt.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
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

    int isVisible(int row,int col) {
        int myValue = value(row,col);
        List<Integer> myRow = row(row).collect(Collectors.toList());
        List<Integer> myCol = col(col).collect(Collectors.toList());
        System.err.println(row+","+col+"["+myValue+"] myRow"+myRow+" myCol"+myCol);
        if(!IntStream.range(0,col).map(myRow::get).filter(v -> v>=myValue).findFirst().isPresent()) {
            return 1;// visible from left
        } else if(!IntStream.range(0,row).map(myCol::get).filter(v -> v>=myValue).findFirst().isPresent()) {
            return 2;// visible from top
        }
        if(!IntStream.range(0,myRow.size()-col)
                .map(i -> myRow.get(myRow.size()-i-1)).filter(v -> v>=myValue).findFirst().isPresent()) {
            return 3;// visible from right
        } else if(!IntStream.range(0,myCol.size()-row)
                .map(i -> myCol.get(myCol.size()-i-1)).filter(v -> v>=myValue).findFirst().isPresent()) {
            return 4;// visible from bottom
        }
        return 0;
    }

    int countVisibleTrees() {
        var size = size();
        int count = 0;
        for(int row=0;row<size.height;row++) {
            for(int col=0;col<size.width;col++) {
                if(isVisible(row,col)>0) {
                    count++;
                }
            }
        }
        return count;
    }
}
