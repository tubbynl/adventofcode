package nl.tubby.aoc23;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Day2KubeGame {


    record Game(int id, int red, int green, int blue) {
        static Game parse(String rawValue) {

            int id = Integer.parseInt(StringUtils.substringAfter(StringUtils.substringBefore(rawValue,":")," "));
            int red = KubeCount.parse(rawValue).filter(c -> "red".equals(c.color())).mapToInt(KubeCount::amount).max().orElse(0);
            int green = KubeCount.parse(rawValue).filter(c -> "green".equals(c.color())).mapToInt(KubeCount::amount).max().orElse(0);
            int blue = KubeCount.parse(rawValue).filter(c -> "blue".equals(c.color())).mapToInt(KubeCount::amount).max().orElse(0);
            return new Game(id,red,green,blue);
        }

        boolean isPossible() {
            return red()<=12 && green()<=13 && blue()<=14;
        }
    }

    record KubeCount(int amount, String color) {
        private static final Pattern PATTERN = Pattern.compile("(([0-9]+)\\s+([rgb][a-z]+))+");// group 1=`1 green`, 2=`1`,3=`green`

        static Stream<KubeCount> parse(String rawValue) {
            return PATTERN
                    .matcher(rawValue)
                    .results()
                    .map(r -> new KubeCount(Integer.parseInt(r.group(2)),StringUtils.trimToNull(StringUtils.lowerCase(r.group(3)))));
        }
    }
}
