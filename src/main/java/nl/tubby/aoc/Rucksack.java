package nl.tubby.aoc;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public record Rucksack(String compartment1, String compartment2) {
    static Rucksack parse(String line) {
        String firstHalf = StringUtils.substring(line,0,line.length()/2);
        String secondHalf = StringUtils.substring(line,line.length()/2);
        return new Rucksack(firstHalf,secondHalf);
    }
    String full() {
        return compartment1()+compartment2();
    }

    boolean contains(String ch) {
        return compartment1().contains(ch) || compartment2().contains(ch);
    }

    static Stream<String> distinctChars(String value) {
       return  value.chars()
                .distinct()
                .mapToObj(i -> Character.valueOf((char)i))
                .map(Object::toString);
    }

    String intersect() {
        return distinctChars(compartment2())
                .filter(s -> compartment1().contains(s))
                .collect(Collectors.joining());
    }

    public int priority() {
        return calcPriority(intersect());
    }

    protected static int calcPriority(String input) {
        return input.chars()
                .map(Rucksack::charToPrority)
                .sum();
    }

    protected static int charToPrority(int ch) {
        int correction = Character.isUpperCase((char)ch)?38:96;
        return ch-correction;
    }
}

class RucksackSlurper extends Slurper<Rucksack> {

    public RucksackSlurper(String path, String fileName) {
        super(path, fileName);
    }

    @Override
    protected Rucksack build(String line) {
        return Rucksack.parse(line);
    }

}

record ThreeElves(Rucksack[] sacks) {
    String intersect() {
        String firstResult = intersect(sacks[0].full(),sacks[1]);
        return intersect(firstResult,sacks[2]);
    }

    int priority() {
        return Rucksack.calcPriority(intersect());
    }

    static String intersect(String input, Rucksack two) {
        return Rucksack.distinctChars(input)
                .filter(two::contains)
                .collect(Collectors.joining());
    }
}

class ThreeElvesSlurper extends Slurper<ThreeElves> {
    private final List<Rucksack> rucksacks = new ArrayList<>();
    public ThreeElvesSlurper(String path, String fileName) {
        super(path, fileName);
    }

    @Override
    protected ThreeElves build(String line) {
        this.rucksacks.add(Rucksack.parse(line));
        if(rucksacks.size()<3) {
            return null;
        }
        var threeElves = new ThreeElves(rucksacks.toArray(new Rucksack[3]));
        rucksacks.clear();
        return threeElves;
    }
}
