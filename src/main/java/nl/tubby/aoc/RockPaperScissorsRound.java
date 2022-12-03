package nl.tubby.aoc;

import static nl.tubby.aoc.RockPaperScissors.*;
record RockPaperScissorsRound(RockPaperScissors opponent, RockPaperScissors me) {

    RoundState outcome() {
        if(me.equals(opponent)) {
            return RoundState.tie;
        }
        if(me.equals(rock) && opponent.equals(scissors)) {
            return RoundState.win;
        }
        if(me.equals(scissors) && opponent.equals(paper)) {
            return RoundState.win;
        }
        if(me.equals(paper) && opponent.equals(rock)) {
            return RoundState.win;
        }
        return RoundState.loss;
    }

    int calculateScore() {
        return me.getScore() + outcome().getScore();
    }
}

