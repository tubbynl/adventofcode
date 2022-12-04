package nl.tubby.aoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

public class Slurper<T extends Object> {
    private final Function<String,T> parser;

    public Slurper(Function<String, T> parser) {
        this.parser = parser;
    }

    protected Stream<String> stream(Path path) {
        try {
            return Files.lines(path);
        } catch (IOException e) {
            System.err.println("unable to stream "+path+": "+e.getMessage());
            return Stream.empty();
        }
    }

    protected T build(String line) {
        return this.parser.apply(line);
    }

    public Stream<T> slurp(Path path) {
        return stream(path)
                .map(this::build)
                .filter(Objects::nonNull);
    }
}
