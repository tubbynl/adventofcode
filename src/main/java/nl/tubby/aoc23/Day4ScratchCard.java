package nl.tubby.aoc23;

import org.apache.commons.lang3.StringUtils;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day4ScratchCard {

    record ScratchCard(int nr,int wins, int score) {

        static ScratchCard parse(String input) {
            var splitted = StringUtils.split(input,"|:");
            var nr = Integer.parseInt(StringUtils.trimToEmpty(StringUtils.removeStart(splitted[0],"Card")));
            var winning = split(splitted[1]).collect(Collectors.toUnmodifiableSet());
            int wins = split(splitted[2]).filter(winning::contains).toList().size();
            return new ScratchCard(nr,wins,getPoints(wins));
        }
    }

    static int getPoints(int wins) {
        int points = 0;
        if(wins>0) {
            points = 1;
            for(int i=1;i<wins;i++) {
                points *= 2;
            }
        }
        return points;
    }

    static Stream<Integer> split(String raw) {
        return Stream.of(StringUtils.split(raw))
                .map(StringUtils::trimToEmpty)
                .map(Integer::parseInt);
    }
}
