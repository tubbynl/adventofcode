package nl.tubby.aoc23;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Stream;

public class Day2KubeGame {


    record Game(int id, List<KubeCount> kubes) {
        static Game parse(String rawValue) {
            var splitted = StringUtils.split(rawValue,":",2);
            int id = Integer.parseInt(StringUtils.substringAfter(splitted[0]," "));
            var counts = Stream.of(StringUtils.split(splitted[1],","))
                    .map(StringUtils::trim)
                    .map(KubeCount::parse)
                    .toList();
            return new Game(id,counts);
        }
    }

    record KubeCount(int amount, String color) {
        static KubeCount parse(String rawValue) {
            return new KubeCount(
                    Integer.parseInt(StringUtils.substringBefore(rawValue,' ')),
                    StringUtils.substringAfter(rawValue,' ')
            );
        }
    }
}
