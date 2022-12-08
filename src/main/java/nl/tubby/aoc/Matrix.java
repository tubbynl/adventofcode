package nl.tubby.aoc;

import org.apache.commons.lang3.tuple.Pair;

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

    int value(Coordinates coords) {
        if(values().size()<=coords.row()) {
            return 0;
        }
        return getAt(values().get(coords.row()),coords.col());
    }

    static Integer getAt(List<Integer> list,int index) {
        return list.size()<=index?0:list.get(index);
    }

    Stream<Integer> row(Coordinates coords) {
        return values().size()<=coords.row()? Stream.empty():values().get(coords.row()).stream();
    }

    Stream<Integer> col(Coordinates coords) {
        return values().stream().map(row -> getAt(row,coords.col()));
    }

    Dimension size() {
        return new Dimension(
                this.values.get(0).size(),
                this.values.size());
    }

    List<Integer> toMyLeft(Coordinates coords) {
        return row(coords).collect(Collectors.toList()).subList(0,coords.col());
    }

    List<Integer> toMyTop(Coordinates coords) {
        return col(coords).collect(Collectors.toList()).subList(0,coords.row());
    }

    List<Integer> toMyRight(Coordinates coords) {
        List<Integer> myRow = row(coords).collect(Collectors.toList());
        return myRow.subList(coords.col()+1,myRow.size());
    }

    List<Integer> toMyBottom(Coordinates coords) {
        List<Integer> myCol = col(coords).collect(Collectors.toList());
        return myCol.subList(coords.row()+1,myCol.size());
    }

    boolean isVisible(Coordinates coords) {
        return visibleFrom(coords)>0;
    }
    int visibleFrom(Coordinates coords) {
        int myValue = value(coords);
        if(!containsGte(toMyLeft(coords),myValue)) {
            return 1;// visible from left
        } else if(!containsGte(toMyTop(coords),myValue)) {
            return 2;// visible from top
        } else if(!containsGte(toMyRight(coords),myValue)) {
            return 3;// visible from right
        } else if(!containsGte(toMyBottom(coords),myValue)) {
            return 4;// visible from bottom
        }
        return 0;
    }

    static boolean containsGte(List<Integer> list,Integer min) {
        //System.err.println(list+" has >="+min);
        return list.stream().filter(v -> v>=min).findFirst().isPresent();
    }

    Stream<Coordinates> stream() {
        var size = size();
        List<Coordinates> coords = new ArrayList<>();
        for(int row=0;row<size.height;row++) {
            for(int col=0;col<size.width;col++) {
                coords.add(new Coordinates(row,col));
            }
        }
        return coords.stream();
    }

    long countVisibleTrees() {
        return stream()
                .filter(this::isVisible)
                .count();
    }
}

record Coordinates(int row,int col) {}