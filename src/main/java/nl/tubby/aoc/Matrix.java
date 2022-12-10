package nl.tubby.aoc;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

record Matrix(List<List<Integer>> values, int width,int height) {

    static Matrix slurp(Path path) {
        var slurper = new Slurper<>(Matrix::parse);
        var matrix = slurper.slurp(path).toList();
        return new Matrix(matrix,matrix.get(0).size(),matrix.size());
    }

    static List<Integer> parse(String line) {
        return line.chars()
                .filter(Character::isDigit)
                .mapToObj(Character::getNumericValue)
                .toList();
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

    List<Integer> toMyLeft(Coordinates coords) {
        List<Integer> result =  row(coords).collect(Collectors.toList()).subList(0,coords.col());
        Collections.reverse(result);
        return result;
    }

    List<Integer> toMyTop(Coordinates coords) {
        List<Integer> result = col(coords).collect(Collectors.toList()).subList(0,coords.row());
        Collections.reverse(result);
        return result;
    }

    List<Integer> toMyRight(Coordinates coords) {
        List<Integer> myRow = row(coords).collect(Collectors.toList());
        return myRow.subList(coords.col()+1,width);
    }

    List<Integer> toMyBottom(Coordinates coords) {
        List<Integer> myCol = col(coords).collect(Collectors.toList());
        return myCol.subList(coords.row()+1,height);
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
        return IntStream.range(0,height)
                .boxed()
                .flatMap(row -> IntStream.range(0,width).mapToObj(col -> new Coordinates(row,col)));
    }

    long countVisibleTrees() {
        return stream()
                .filter(this::isVisible)
                .count();
    }

    int scenicScore(List<Integer> list,Integer myTreeHeight) {
        for(int i=0;i< list.size();i++) {
            if(list.get(i)>=myTreeHeight) {
                return i+1;
            }
        }
        return list.size();
    }

    int scenicScore(Coordinates coords) {
        var myTreeHeight = value(coords);
        return scenicScore(toMyLeft(coords),myTreeHeight)
                * scenicScore(toMyTop(coords),myTreeHeight)
                * scenicScore(toMyRight(coords),myTreeHeight)
                * scenicScore(toMyBottom(coords),myTreeHeight);
    }
}

record Coordinates(int row,int col) {
    static Coordinates parse(String value) {
        int[] splitted = Stream.of(StringUtils.split(value,"-,.",2))
                .mapToInt(NumberUtils::toInt).toArray();
        return new Coordinates(splitted[0],splitted[1]);
    }

    Coordinates moveTail(Coordinates head) {
        int rowDistance = head.row-row;
        int colDistance = head.col-col;
        System.err.print("distance "+rowDistance+"."+colDistance+" ");
        // if distance not larger than 1 we dont need to move tail
        if(!IntStream.of(rowDistance,colDistance)
                .map(Math::abs)
                .filter(i->i>1).findFirst().isPresent()) {
            return this;
        }
        int colStep = colDistance>0?-1:1;
        int rowStep = rowDistance>0?-1:1;
        if(Math.abs(colDistance)>Math.abs(rowDistance)) {
            rowStep=0;
        } else if(Math.abs(rowDistance)>Math.abs(colDistance)) {
            colStep=0;
        }
        return head.at(rowStep,colStep);
    }

    Coordinates at(int stepRow,int stepCol) {
        System.err.print("steps "+stepRow+" "+stepCol+" to " );
        return new Coordinates(row()+stepRow,col()+stepCol);
    }

    @Override
    public String toString() {
        return row+"."+ col;
    }
}