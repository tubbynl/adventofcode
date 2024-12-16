package nl.tubby.aoc24;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public record Report(List<Integer> levels) {

        public static Report parse(String line) {
            return new Report(Arrays.stream(StringUtils.split(line,' '))
                    .map(NumberUtils::toInt)
                    .toList());
        }

        private List<LevelChain> chain() {
            var result = new ArrayList<LevelChain>();
            for(int i=0;i<levels().size()-1;i++) {
                result.add(new LevelChain(levels().get(i),levels().get(i+1)));
            }
            return result;
        }

        boolean isSafe() {
            var chain = chain();
            var directions = chain.stream().map(LevelChain::direction).collect(Collectors.toSet());
            if(directions.size()>1 || directions.contains(LevelChain.Direction.none)) {
                return false;
            }
            return chain.stream().noneMatch(level -> level.difference()>3);
        }

        boolean isSafeOrHasDamperedSafe() {
            return isSafe() || dampered().anyMatch(Report::isSafe);
        }

        /**
         * apply damper; remove first Report being faulty
         */
        private Stream<Report> dampered() {
            var result = new ArrayList<Report>();
            for(int i=0;i<levels().size();i++) {
                var splittedWithoutItem = new ArrayList<>(levels());
                splittedWithoutItem.remove(i);
                result.add(new Report(splittedWithoutItem));
            }
            return result.stream();
        }
    }
}
