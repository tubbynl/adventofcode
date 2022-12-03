package nl.tubby.aoc;

import static nl.tubby.aoc.RockPaperScissors.*;
record RockPaperScissorsRound(RockPaperScissors opponent, RockPaperScissors me) {

    RoundState outcome() {
        return RoundState.determine(opponent,me);
    }

    int calculateScore() {
        return me.getScore() + outcome().getScore();
    }
}

