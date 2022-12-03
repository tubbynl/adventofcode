package nl.tubby.aoc;

import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum RockPaperScissors {
    rock(1,"A","X"),
    paper(2, "B","Y"),
    scissors(3,"C","Z");

    private static final Map<String,RockPaperScissors> VALUES = Stream.of(values())
            .flatMap(RockPaperScissors::asEntries)
            .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
    public static final Map<RockPaperScissors,RockPaperScissors> WINS = Map.of(
            rock,scissors,//Rock defeats Scissors
            scissors,paper,//Scissors defeats Paper
            paper,rock//Paper defeats Rock
    );
    private final Set<String> aliases;
    private final int score;

    RockPaperScissors(int score,String... aliases) {
        this.aliases = Set.of(aliases);
        this.score = score;
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

    public int getScore() {
        return score;
    }

    RoundState determineOutcome(RockPaperScissors opponent) {
        if(equals(opponent)) {
            return RoundState.tie;
        }
        if(WINS.get(this).equals(opponent)) {
            return RoundState.win;
        }
        return RoundState.loss;
    }
}
