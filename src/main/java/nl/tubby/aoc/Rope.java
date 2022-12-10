package nl.tubby.aoc;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

class Rope {
    Coordinates head;
    Coordinates tail;

    public Rope(Coordinates start) {
        this.head = start;
        this.tail = start;
    }

    Stream<Coordinates> move(String line) {
        return moveHead(line)
                .peek(head -> System.err.println("head: "+head))
                .flatMap(head -> moveTail(head,this.tail))
                .peek(tail -> System.err.println("tail: "+tail))
                .peek(tail -> this.tail = tail);
    }

    static Stream<Coordinates> moveTail(Coordinates head, Coordinates currentTail) {
        if(head.distance(currentTail)<=1) {
            return Stream.of(currentTail);
        }
        int stepRow = 0;
        int stepCol = 0;
        if(currentTail.sameRow(head)) {
            stepCol = head.col()>currentTail.col()?-1:1;
        } else if(currentTail.sameCol(head)) {
            stepRow = head.row()>currentTail.row()?-1:1;
        } else {
            stepCol = head.col()>currentTail.col()?-1:1;
            stepRow = head.row()>currentTail.row()?-1:1;
        }
        Coordinates tail = new Coordinates(head.row()+stepRow,head.col()+stepCol);
        return Stream.of(tail);
    }

    Stream<Coordinates> moveHead(String line) {
        var move = Direction.parse(line);
        return move.getLeft()
                .move(this.head,move.getRight())
                .peek(pos -> this.head=pos);
    }

    static long countTailPositions(Coordinates start,Path path) {
        var slurper = new Slurper<>(new Rope(start)::move);
        return slurper.slurp(path).flatMap(Function.identity()).distinct().count();
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

    Stream<Coordinates> move(Coordinates start,int steps) {
        if (steps <= 0) {
            return Stream.empty();
        }
        Coordinates current = start;
        List<Coordinates> result = new ArrayList<>();
        for(int i=0;i<steps;i++) {
            var next = step(current);
            result.add(next);
            current = next;
        }
        return result.stream();
    }
}
