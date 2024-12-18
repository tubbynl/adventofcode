package nl.tubby.aoc22;

import nl.tubby.Resource;
import nl.tubby.Slurper;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

class Rope {
    static final Logger log = LoggerFactory.getLogger(Rope.class);
    final int length;
    Coordinates head;
    Coordinates tail;

    public Rope(int length,Coordinates start) {
        this.length = length;
        this.head = start;
        this.tail = start;
    }

    Stream<Coordinates> move(String line) {
        return Stream.of(Movement.parse(line))
                .flatMap(this::moveHead)
                .peek(head -> log.debug("H:{}",this.head))
                .map(c -> this.tail.moveTail(this.head,this.length))
                .peek(tail -> this.tail = tail);
    }

    Stream<Coordinates> moveHead(Movement move) {
        return move.apply(this.head)
                .peek(pos -> this.head=pos);
    }

    static long countTailPositions(Coordinates start, Resource path, int length) {
        var rope = new Rope(length,start);
        return new Slurper<>(rope::move)
                .slurp(path)
                .flatMap(Function.identity())
                .distinct()
                .count();
    }
}

enum Direction {
    left,
    up,
    right,
    down;

    static final Map<Character,Direction> VALUES = Map.of('L',left,'U',up,'R',right,'D',down);

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

    @Override
    public String toString() {
        return name().substring(0,1).toUpperCase();
    }
}

record Movement(Direction direction,int steps) {
    static Movement parse(String line) {
        var direction = Direction.VALUES.get(line.charAt(0));
        int steps = NumberUtils.toInt(StringUtils.substringAfter(line," "));
        return new Movement(direction,steps);
    }

    Stream<Coordinates> apply(Coordinates start) {
        Rope.log.debug("{} on H:{}",this,start);
        return this.direction.move(start, this.steps);
    }

    @Override
    public String toString() {
        return "== " + direction+ " "+steps +" ==";
    }
}