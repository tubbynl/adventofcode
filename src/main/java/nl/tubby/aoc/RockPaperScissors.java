package nl.tubby.aoc;

import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum RockPaperScissors {
    rock("A","X"),
    paper("B","Y"),
    scissor("C","Z");

    private static final Map<String,RockPaperScissors> VALUES = Stream.of(values())
            .flatMap(RockPaperScissors::asEntries)
            .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
    private final Set<String> aliases;

    RockPaperScissors(String... aliases) {
        this.aliases = Set.of(aliases);
    }

    private Stream<Map.Entry<String, RockPaperScissors>> asEntries() {
        return this.aliases.stream().map(k -> new HashMap.SimpleEntry(k,this));
    }

    public static Optional<RockPaperScissors> parse(String value) {
        return Optional.ofNullable(value)
                .map(StringUtils::trimToNull)
                .map(StringUtils::upperCase)
                .map(VALUES::get);
    }
}
