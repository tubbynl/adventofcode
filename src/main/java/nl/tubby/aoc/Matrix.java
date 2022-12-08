package nl.tubby.aoc;

import java.awt.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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

    List<Integer> toMyLeft(int row,int col) {
        return row(row).collect(Collectors.toList()).subList(0,col);
    }

    List<Integer> toMyTop(int row,int col) {
        return col(col).collect(Collectors.toList()).subList(0,row);
    }

    List<Integer> toMyRight(int row,int col) {
        List<Integer> myRow = row(row).collect(Collectors.toList());
        return myRow.subList(col+1,myRow.size());
    }

    List<Integer> toMyBottom(int row,int col) {
        List<Integer> myCol = col(col).collect(Collectors.toList());
        return myCol.subList(row+1,myCol.size());
    }

    int isVisible(int row,int col) {
        int myValue = value(row,col);
        if(!containsGte(toMyLeft(row,col),myValue)) {
            return 1;// visible from left
        } else if(!containsGte(toMyTop(row,col),myValue)) {
            return 2;// visible from top
        } else if(!containsGte(toMyRight(row,col),myValue)) {
            return 3;// visible from right
        } else if(!containsGte(toMyBottom(row,col),myValue)) {
            return 4;// visible from bottom
        }
        return 0;
    }

    static boolean containsGte(List<Integer> list,Integer min) {
        //System.err.println(list+" has >="+min);
        return list.stream().filter(v -> v>=min).findFirst().isPresent();
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
