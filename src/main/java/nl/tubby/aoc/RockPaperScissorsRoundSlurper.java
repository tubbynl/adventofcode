package nl.tubby.aoc;

import org.apache.commons.lang3.StringUtils;

class RockPaperScissorsRoundSlurper extends Slurper<RockPaperScissorsRound> {

    public RockPaperScissorsRoundSlurper(String path, String fileName) {
        super(path,fileName);
    }

    public static RockPaperScissorsRound createRound(String round) {
        String[] split = StringUtils.split(round," ");
        RockPaperScissors opponent = RockPaperScissors.parse(split[0]).get();
        RockPaperScissors me = RockPaperScissors.parse(split[1]).get();
        return new RockPaperScissorsRound(opponent,me,me.determineOutcome(opponent));
    }

    public static RockPaperScissorsRound createRoundPart2(String round) {
        var split = StringUtils.split(round," ");
        var opponent = RockPaperScissors.parse(split[0]).get();
        var requiredOutcome = RoundState.parse(split[1]).get();
        var myMove = opponent.determineMyMoveFor(requiredOutcome);
        return new RockPaperScissorsRound(opponent,myMove,requiredOutcome);
    }
}

