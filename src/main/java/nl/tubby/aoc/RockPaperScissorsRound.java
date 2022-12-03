package nl.tubby.aoc;

record RockPaperScissorsRound(RockPaperScissors opponent, RockPaperScissors me) {

    RoundState outcome() {
        if(opponent().equals(me)) {
            return RoundState.tie;
        }
        return RoundState.win;
    }
}

