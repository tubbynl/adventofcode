package nl.tubby.aoc;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static nl.tubby.aoc.RockPaperScissors.*;

public enum RoundState {
    loss(0, "X"),
    tie(3, "Y"),
    win(6, "Z");

    private static final Map<String,RoundState> MAP = Stream.of(values())
            .collect(Collectors.toMap(RoundState::getAlias, Function.identity()));
    private final int score;
    private final String alias;

    RoundState(int score, String alias) {
        this.score = score;
        this.alias = alias;
    }



    public int getScore() {
        return score;
    }

    public String getAlias() {
        return alias;
    }

    static RoundState determine(RockPaperScissors opponent, RockPaperScissors me) {
        if(me.equals(opponent)) {
            return tie;
        }
        if(me.equals(rock) && opponent.equals(scissors)) {
            return win;
        }
        if(me.equals(scissors) && opponent.equals(paper)) {
            return win;
        }
        if(me.equals(paper) && opponent.equals(rock)) {
            return win;
        }
        return loss;
    }

    static Optional<RoundState> parse(String value) {
        return Optional.ofNullable(value)
                .map(MAP::get);
    }
}
