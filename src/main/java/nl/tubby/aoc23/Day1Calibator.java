package nl.tubby.aoc23;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class Day1Calibator {
    private  static final Map<String,Integer> DIGITS = Map.of(
            "1",1,
            "2",2,
            "3",3,
            "4",4,
            "5",5,
            "6",6,
            "7",7,
            "8",8,
            "9",9,
            "0",0
    );

    private static final Map<String,Integer> ALL;

    static {
        var combined = new HashMap<>(DIGITS);
        combined.putAll(Map.of(
                "one",1,
                "two",2,
                "three",3,
                "four",4,
                "five",5,
                "six",6,
                "seven",7,
                "eight",8,
                "nine",9,
                "zero",0
        ));
        ALL = Collections.unmodifiableMap(combined);
    }

    static Integer parseDigits(String input) {
        return parse(input,DIGITS);
    }

    static Integer parseLetters(String input) {
        return parse(input,ALL);
    }

    private static Integer parse(String input, Map<String,Integer> matches) {
        var first = substringStream(input,s -> s.substring(1))
                .flatMap(s -> findEntry(matches, e -> s.startsWith(e.getKey())))
                .findAny().orElse(0);
        var last = substringStream(input,s -> s.substring(0,s.length()-1))
                .flatMap(s -> findEntry(matches, e -> s.endsWith(e.getKey())))
                .findAny().orElse(0);
        return (first*10)+last;
    }

    private static Stream<Integer> findEntry(Map<String,Integer> matches, Predicate<Map.Entry<String,Integer>> filter) {
        return matches
                .entrySet()
                .stream()
                .filter(filter)
                .map(Map.Entry::getValue);
    }

    static Stream<String> substringStream(final String input, UnaryOperator<String> substringify) {
        return Stream.iterate(input, s -> !s.isEmpty(), substringify);
    }
}
