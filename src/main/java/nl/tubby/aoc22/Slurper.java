package nl.tubby.aoc22;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Stream;

public class Slurper<T> {

    private final Function<String,T> parser;
    private final Predicate<T> filter;

    public Slurper(Function<String, T> parser, Predicate<T>... filters) {
        this.parser = parser;
        this.filter = Stream.concat(Stream.of(Objects::nonNull),Stream.of(filters).filter(Objects::nonNull))
                .reduce(Predicate::and).get();
    }

    private Stream<String> stream(Path path) {
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
                .filter(this.filter);
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

    public long count(Path path) {
        return slurp(path)
                .count();
    }

    public T first(Path path) {
        return slurp(path).findFirst().orElse(null);
    }

    public List<T> list(Path path) {
        return slurp(path).toList();
    }
}
