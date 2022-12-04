package nl.tubby.aoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.stream.Stream;

public abstract class Slurper<T extends Object> {
    private final Path path;

    public Slurper(String path, String fileName) {
        this.path = Path.of(path,fileName);
    }

    protected Stream<String> stream() {
        try {
            return Files.lines(this.path);
        } catch (IOException e) {
            System.err.println("unable to stream "+this.path+": "+e.getMessage());
            return Stream.empty();
        }
    }

    protected abstract T build(String line);

    public Stream<T> slurp() {
        return stream()
                .map(this::build)
                .filter(Objects::nonNull);
    }
}
