package nl.tubby.aoc;

import static nl.tubby.aoc.RockPaperScissors.*;
record RockPaperScissorsRound(RockPaperScissors opponent, RockPaperScissors me,RoundState outcome) {

    int calculateScore() {
        return me.getScore() + outcome().getScore();
    }
}

