package nl.tubby.aoc;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

enum RockPaperScissors {
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

    RockPaperScissors determineMyMoveFor(RoundState state) {
        if(state.equals(RoundState.tie)) {
            return this;
        }
        if(state.equals(RoundState.loss)) {
            return WINS.get(this);
        }
        return WINS.entrySet().stream()
                .filter(e -> e.getValue().equals(this))
                .map(Map.Entry::getKey).findFirst().get();
    }
}

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