package nl.tubby.aoc24;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Arrays;

public class Day1LocationLinker {
    record Pair(int left, int right) {
        int distance() {
            return Math.abs(left - right);
        }
    }

    static Pair parse(String string) {
        var splitted = Arrays.stream(StringUtils.split(string, " ",2))
                .mapToInt(NumberUtils::toInt)
                .toArray();
        return new Pair(splitted[0], splitted[1]);
    }
}
