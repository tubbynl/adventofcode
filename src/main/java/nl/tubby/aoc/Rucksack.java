package nl.tubby.aoc;

import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public record Rucksack(String compartment1, String compartment2) {

    private static List<Integer> asList(String value) {
        return value.chars().boxed().collect(Collectors.toList());
    }
    public static Rucksack parse(String line) {
        String firstHalf = StringUtils.substring(line,0,line.length()/2);
        String secondHalf = StringUtils.substring(line,line.length()/2);
        return new Rucksack(firstHalf,secondHalf);
    }

    String intersect() {
        return compartment2().chars()
                .distinct()
                .mapToObj(i -> Character.valueOf((char)i))
                .map(Object::toString)
                .filter(s -> compartment1().contains(s))
                .collect(Collectors.joining());
    }

    public int priority() {
        return 0;
    }
}

class RucksackSlurper extends Slurper<Rucksack> {

    public RucksackSlurper(String path, String fileName) {
        super(path, fileName);
    }

    public Stream<Rucksack> slurp() {
        return slurp(Rucksack::parse);
    }
}

