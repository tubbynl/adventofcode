package nl.tubby.aoc24;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Day2 {
    public record LevelChain(int a,int b) {
        enum Direction {increasing,decreasing,none}

        Direction direction() {
            return a==b ? Direction.none : a>b? Direction.decreasing: Direction.increasing;
        }

        int difference() {
            return Math.abs(a-b);
        }
    }

    public record Report(List<LevelChain> levels) {

        public static Report parse(String line) {
            var splitted = Arrays.stream(StringUtils.split(line,' '))
                    .map(NumberUtils::toInt)
                    .toList();

            var result = new ArrayList<LevelChain>();
            for(int i=0;i<splitted.size()-1;i++) {
                result.add(new LevelChain(splitted.get(i),splitted.get(i+1)));
            }
            return new Report(Collections.unmodifiableList(result));
        }

        boolean isSafe() {
            var directions = levels().stream().map(LevelChain::direction).collect(Collectors.toSet());
            if(directions.size()>1 || directions.contains(LevelChain.Direction.none)) {
                return false;
            }
            return levels().stream().noneMatch(level -> level.difference()>3);
        }
    }
}
