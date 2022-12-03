package nl.tubby.aoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Function;
import java.util.stream.Stream;

abstract public class Slurper<T extends Object> {
    private final Path path;

    public Slurper(String path, String fileName) {
        this.path = Path.of(path,fileName);
    }

    protected Stream<String> stream() {
        try {
            return Files.readAllLines(this.path)
                    .stream();
        } catch (IOException e) {
            System.err.println("unable to stream "+this.path+": "+e.getMessage());
            return Stream.empty();
        }
    }

    public Stream<T> slurp(Function<String,T> apply) {
        return stream()
                .map(apply);
    }
}
