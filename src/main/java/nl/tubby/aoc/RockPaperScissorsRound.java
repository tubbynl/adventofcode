package nl.tubby.aoc;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

record RockPaperScissorsRound(RockPaperScissors opponent, RockPaperScissors me,RoundState outcome) {
    int calculateScore() {
        return me.getScore() + outcome().getScore();
    }

    static RockPaperScissorsRound build(String round) {
        String[] split = StringUtils.split(round," ");
        RockPaperScissors opponent = RockPaperScissors.parse(split[0]).get();
        RockPaperScissors me = RockPaperScissors.parse(split[1]).get();
        return new RockPaperScissorsRound(opponent,me,me.determineOutcome(opponent));
    }

    static RockPaperScissorsRound build2(String round) {
        var split = StringUtils.split(round," ");
        var opponent = RockPaperScissors.parse(split[0]).get();
        var requiredOutcome = RoundState.parse(split[1]).get();
        var myMove = opponent.determineMyMoveFor(requiredOutcome);
        return new RockPaperScissorsRound(opponent,myMove,requiredOutcome);
    }
}

class RockPaperScissorsRoundSlurper extends Slurper<RockPaperScissorsRound> {
    public RockPaperScissorsRoundSlurper() {
        super(RockPaperScissorsRound::build);
    }
}

class RockPaperScissorsRoundSlurper2 extends Slurper<RockPaperScissorsRound> {
    public RockPaperScissorsRoundSlurper2() {
        super(RockPaperScissorsRound::build2);
    }
}

enum RoundState {
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

    static Optional<RoundState> parse(String value) {
        return Optional.ofNullable(value)
                .map(MAP::get);
    }
}
