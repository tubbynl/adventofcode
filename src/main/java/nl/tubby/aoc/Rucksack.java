package nl.tubby.aoc;

import org.apache.commons.lang3.StringUtils;

public record Rucksack(String compartment1, String compartment2) {

    public static Rucksack parse(String line) {
        String firstHalf = StringUtils.substring(line,0,line.length()/2);
        String secondHalf = StringUtils.substring(line,line.length()/2);
        return new Rucksack(firstHalf,secondHalf);
    }
}

