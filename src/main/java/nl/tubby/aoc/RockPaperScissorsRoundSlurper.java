package nl.tubby.aoc;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Function;
import java.util.stream.Stream;

class RockPaperScissorsRoundSlurper{
    private final Path path;

    public RockPaperScissorsRoundSlurper(String path, String fileName) {
        this.path = Path.of(path,fileName);
    }

    private Stream<String> stream() {
        try {
            return Files.readAllLines(this.path)
                    .stream();
        } catch (IOException e) {
            System.err.println("unable to open "+this.path+": "+e.getMessage());
            return Stream.empty();
        }
    }

    public Stream<RockPaperScissorsRound> slurp(Function<String,RockPaperScissorsRound> apply) {
        return stream()
                .map(apply);
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

