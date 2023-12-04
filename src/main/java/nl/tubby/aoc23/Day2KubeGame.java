package nl.tubby.aoc23;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day2KubeGame {


    record Game(int id, int red, int green, int blue) {
        static final Collector<KubeCount, ?, Map<String, Integer>> KUBE_COUNT_MAP_COLLECTOR = Collectors.groupingBy(KubeCount::color, Collectors.summingInt(KubeCount::amount));

        static Game parse(String rawValue) {

            int id = Integer.parseInt(StringUtils.substringAfter(StringUtils.substringBefore(rawValue,":")," "));
            var result = KubeCount.parse(rawValue)
                    .collect(KUBE_COUNT_MAP_COLLECTOR);
            if(result.size()!=3) {
                throw new RuntimeException("invalid color count: "+result.keySet());
            }
            int red = result.get("red");
            int green = result.get("green");
            int blue = result.get("blue");
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
