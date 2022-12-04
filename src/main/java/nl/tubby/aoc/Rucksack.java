package nl.tubby.aoc;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public record Rucksack(String compartment1, String compartment2) {

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
        return intersect().chars()
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

    public Stream<Rucksack> slurp() {
        return slurp(Rucksack::parse);
    }
}

record ThreeElves(Rucksack[] sacks) {
}

class ThreeElvesSlurper extends Slurper<ThreeElves> {

    private final List<Rucksack> rucksacks = new ArrayList<>();
    public ThreeElvesSlurper(String path, String fileName) {
        super(path, fileName);
    }

    public Stream<ThreeElves> build() {
        return stream()
                .flatMap(this::parse);
    }

    private Stream<ThreeElves> parse(String line) {
        this.rucksacks.add(Rucksack.parse(line));
        if(rucksacks.size()<3) {
            return Stream.empty();
        }
        var threeElves = new ThreeElves(rucksacks.toArray(new Rucksack[3]));
        rucksacks.clear();
        return Stream.of(threeElves);
    }
}
