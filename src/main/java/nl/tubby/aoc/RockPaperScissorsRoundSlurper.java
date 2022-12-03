package nl.tubby.aoc;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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

    public Stream<RockPaperScissorsRound> slurp() {
        return stream()
                .map(RockPaperScissorsRoundSlurper::createRound);
    }

    private static RockPaperScissorsRound createRound(String round) {
        String[] split = StringUtils.split(round," ");
        return new RockPaperScissorsRound(
                RockPaperScissors.parse(split[0]).get(),
                RockPaperScissors.parse(split[1]).get()
        );
    }
}

