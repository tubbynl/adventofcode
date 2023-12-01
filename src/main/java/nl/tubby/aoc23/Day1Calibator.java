package nl.tubby.aoc23;

import java.util.Optional;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
        var first = extractFirstMatch(input,matches);
        var last = extractLastMatch(input,matches);
        return (first*10)+last;
    }

    private static int extractFirstMatch(String input, Map<String,Integer> matches) {
        return substringStream(input,false)
                .map(s -> startsWithValue(s,matches))
                .flatMap(Optional::stream)
                .findFirst()
                .orElse(0);
    }

    private static Optional<Integer> startsWithValue(String input, Map<String,Integer> matches) {
        for (var entry:matches.entrySet()) {
            if(input.startsWith(entry.getKey())) {
                return Optional.of(entry.getValue());
            }
        }
        return Optional.empty();
    }

    private static int extractLastMatch(String input, Map<String,Integer> matches) {
        var current = input;
        while(!current.isEmpty()) {
            for (var entry:matches.entrySet()) {
                if(current.endsWith(entry.getKey())) {
                    return entry.getValue();
                }
            }
            current = current.substring(0,current.length()-1);
        }
        return 0;
    }

    static Stream<String> substringStream(final String input, boolean backwards) {
        UnaryOperator<String> substringify = backwards? s -> s.substring(0,s.length()-1): s -> s.substring(1);
        return Stream.iterate(input, s -> !s.isEmpty(), substringify);
    }
}
