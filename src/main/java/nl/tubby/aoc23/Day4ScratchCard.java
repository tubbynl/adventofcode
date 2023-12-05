package nl.tubby.aoc23;

import org.apache.commons.lang3.StringUtils;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day4ScratchCard {

    static Integer parseScore(String input) {
        var splitted = StringUtils.split(input,"|:");
        var winning = split(splitted[1]);
        var myNumbers = split(splitted[2]);

        var wins = myNumbers.stream().filter(winning::contains).count();
        int points = 0;
        if(wins>0) {
            points = 1;
            for(int i=1;i<wins;i++) {
                points *= 2;
            }
        }
        return points;
    }

    static Set<Integer> split(String raw) {
        return Stream.of(StringUtils.split(raw))
                .map(StringUtils::trimToEmpty)
                .map(Integer::parseInt)
                .collect(Collectors.toUnmodifiableSet());
    }
}
