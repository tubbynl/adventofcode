package nl.tubby.aoc;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

record Matrix(List<List<Integer>> values) {

    Matrix() {
        this(new ArrayList<>());
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

    static int getAt(List<Integer> list,int index) {
        return list.size()<=index?0:list.get(index);
    }

    IntStream row(int row) {
        return values().size()<=row?IntStream.empty():values().get(row).stream().mapToInt(Integer::intValue);
    }

    IntStream col(int col) {
        return values().stream().mapToInt(row -> getAt(row,col));
    }
}
