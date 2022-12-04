package nl.tubby.aoc;

import org.apache.commons.lang3.StringUtils;

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

