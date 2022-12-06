package nl.tubby.aoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Stream;

public class Slurper<T extends Object> {

    private final Function<String,T> parser;
    private final Predicate<T> filter;
    private final Optional<String> eof;

    public Slurper(Function<String, T> parser, Optional<String> eof, Predicate<T>... filters) {
        this.parser = parser;
        this.eof = eof;
        this.filter = Stream.concat(Stream.of(Objects::nonNull),Stream.of(filters))
                .reduce(Predicate::and).get();
    }

    public Slurper(Function<String, T> parser) {
        this(parser,Optional.empty());
    }

    private Stream<String> stream(Path path) {
        try {
            return Stream.concat(Files.lines(path),this.eof.stream());
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

    public long count(Path path, Predicate<T> filter) {
        return slurp(path)
                .filter(filter)
                .count();
    }

    public T first(Path path) {
        return slurp(path).findFirst().orElse(null);
    }
}
