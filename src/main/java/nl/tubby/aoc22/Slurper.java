package nl.tubby.aoc22;

import nl.tubby.Resource;

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

    protected T build(String line) {
        return this.parser.apply(line);
    }

    public Stream<T> slurp(Resource resource) {
        return resource.stream(this.parser.getClass())
                .map(this::build)
                .filter(this.filter);
    }

    public int max(Resource resource, ToIntFunction<T> function) {
        return slurp(resource)
                .mapToInt(function)
                .max().orElse(0);
    }

    public int sum(Resource resource,ToIntFunction<T> function) {
        return slurp(resource)
                .mapToInt(function)
                .sum();
    }

    public long count(Resource resource) {
        return slurp(resource)
                .count();
    }

    public T first(Resource resource) {
        return slurp(resource).findFirst().orElse(null);
    }

    public List<T> list(Resource resource) {
        return slurp(resource).toList();
    }
}
