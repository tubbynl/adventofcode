package nl.tubby.aoc;

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
}
