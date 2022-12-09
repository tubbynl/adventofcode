package nl.tubby.aoc;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.nio.file.Path;
import java.util.*;
import java.util.stream.Stream;

class Rope {
    Coordinates head;
    Coordinates tail;

    public Rope(Coordinates start) {
        this.head = start;
        this.tail = start;
    }

    Coordinates move(String line) {
        var newCoords = moveHead(line);
        this.tail = newCoords.size()<=1?this.tail:newCoords.get(newCoords.size()-2);
        return this.tail;
    }

    List<Coordinates> moveHead(String line) {
        var move = Direction.parse(line);
        var newCoords = move.getLeft().move(this.head,move.getRight());
        this.head = newCoords.isEmpty()?this.head:newCoords.get(newCoords.size()-1);
        return newCoords;
    }

    static long countTailPositions(Path path) {
        var rope = new Rope(new Coordinates(0,0));
        var slurper = new Slurper<>(rope::move);
        var tailPositions = Stream.concat(Stream.of(rope.tail),slurper.slurp(path));
        return tailPositions.distinct().count();
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
