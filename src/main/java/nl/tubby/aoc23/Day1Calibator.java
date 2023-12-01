package nl.tubby.aoc23;

import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Day1Calibator {



    static Integer parse(String input) {
        var onlyDigits = StringUtils.getDigits(input);

        var first = StringUtils.substring(onlyDigits,0,1);
        var last = StringUtils.substring(onlyDigits,-1);
        return Integer.parseInt(first+last);
    }

    static final Map<String,Integer> DIGITS = Map.of(
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

    static final Map<String,Integer> WORDS = Map.of(
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
    );

    static final Map<String,Integer> ALL;

    static {
        var combined = new HashMap<String,Integer>();
        combined.putAll(WORDS);
        combined.putAll(DIGITS);
        ALL = Collections.unmodifiableMap(combined);
    }

    static Integer parseLetters(String input) {
        var first = extractFirstMatch(input);
        var last = extractLastMatch(input);
        return (first*10)+last;
    }

    static int extractFirstMatch(String input) {
        var current = input;
        while(current!=null && current.length()>0) {
            for (var entry:ALL.entrySet()) {
                if(current.startsWith(entry.getKey())) {
                    return entry.getValue();
                }
            }
            current = current.substring(1);
        }
        return -1;
    }

    static int extractLastMatch(String input) {
        var current = input;
        while(current!=null && current.length()>0) {
            for (var entry:ALL.entrySet()) {
                if(current.endsWith(entry.getKey())) {
                    return entry.getValue();
                }
            }
            current = current.substring(0,current.length()-1);
        }
        return -1;
    }
}
