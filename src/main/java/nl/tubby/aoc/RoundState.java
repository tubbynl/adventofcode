package nl.tubby.aoc;

import static nl.tubby.aoc.RockPaperScissors.*;

public enum RoundState {
    loss(0),
    tie(3),
    win(6);

    private final int score;

    RoundState(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
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
}
