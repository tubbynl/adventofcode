package nl.tubby.aoc24;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day2 {
    public record Report(List<Integer> levels) {
        public Report(String line) {
            this(Arrays.stream(StringUtils.split(line,' '))
                    .map(NumberUtils::toInt)
                    .toList());
        }

        boolean isSafe() {
            var levels = levels().iterator();
            var current = levels.next();

            while(levels.hasNext()) {
                var next = levels.next();
                var diff = Math.abs(next - current);
                if (diff > 2) {
                    return false;
                }
                current = next;
            }
            return true;
        }
    }
}
