package nl.tubby.aoc;

import java.util.Random;

/**
 * Abel game "de weghalers"
 */
public class AbelDeWegHalers {
    enum Action{
        ONE(1,null), // 1 er bij
        TWO(-2,null), // 2 er af
        THREE(1,null), // 1 er bij
        FOUR(1,null), // 1 er bij
        FIVE(null,5), // 5 poppetjes hebben
        SIX(-1,null); // 1 er af

        final Integer add;
        final Integer set;

        Action(Integer add, Integer set) {
            this.add = add;
            this.set = set;
        }
    }

    int poppetjes = 7;
    private final Random random = new Random();
    private final Action[] possibleActions = Action.values();

    Action throwTheDice() {
        return possibleActions[random.nextInt(possibleActions.length)];
    }

    boolean apply(Action action) {
        if(action.set!=null) {
            this.poppetjes = action.set;
        } else {
            this.poppetjes += action.add;
        }
        return poppetjes<=0;
    }

    /**
     * run the game until it is done
     * @return nr of rounds
     */
    int runUntilWon() {
        int rounds = 0;
        while(!apply(throwTheDice())) {
            rounds++;
        }
        return rounds;
    }
}
