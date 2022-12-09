package nl.tubby.aoc;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Rope {
    private Coordinates current;

    public Rope(Coordinates start) {
        this.current = start;
    }

    Stream<Coordinates> move(String line) {
        var move = Direction.parse(line);
        var newCoords = move.getLeft().move(this.current,move.getRight());
        this.current = newCoords.isEmpty()?this.current:newCoords.get(newCoords.size()-1);
        return Stream.concat(Stream.of(this.current),newCoords.stream());
    }

    Coordinates current() {
        return this.current;
    }
}

enum Direction {
    left,
    up,
    right,
    down;

    static final Map<Character,Direction> VALUES = Map.of('L',left,'U',up,'R',right,'D',down);
    static Pair<Direction,Integer> parse(String line) {
        var direction = VALUES.get(line.charAt(0));
        int steps = NumberUtils.toInt(StringUtils.substringAfter(line," "));
        return Pair.of(direction,steps);
    }

    Coordinates step(Coordinates current) {
        switch(this) {
            case up: return new Coordinates(current.row(), current.col()-1);
            case down: return new Coordinates(current.row(), current.col()+1);
            case right: return new Coordinates(current.row()+1, current.col());
            case left: return new Coordinates(current.row()-1, current.col());
            default: throw new RuntimeException("what direction? "+this);
        }
    }

    List<Coordinates> move(Coordinates start,int steps) {
        if (steps <= 0) {
            return Collections.emptyList();
        }
        Coordinates current = start;
        List<Coordinates> result = new ArrayList<>();
        for(int i=0;i<steps;i++) {
            var next = step(current);
            result.add(next);
            current = next;
        }
        return result;
    }
}
