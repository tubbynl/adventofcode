package nl.tubby.aoc;

record RockPaperScissorsRound(RockPaperScissors opponent, RockPaperScissors me,RoundState outcome) {

    int calculateScore() {
        return me.getScore() + outcome().getScore();
    }
}

