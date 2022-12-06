package nl.tubby.aoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.ToIntFunction;
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
            throw new RuntimeException("unable to stream "+path,e);
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

    public int max(Path path,ToIntFunction<T> function) {
        return slurp(path)
                .mapToInt(function)
                .max().orElse(0);
    }

    public int sum(Path path,ToIntFunction<T> function) {
        return slurp(path)
                .mapToInt(function)
                .sum();
    }

    public T first(Path path) {
        return slurp(path).findFirst().orElse(null);
    }
}
