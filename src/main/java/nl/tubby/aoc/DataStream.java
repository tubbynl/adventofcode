package nl.tubby.aoc;

import java.nio.file.Path;
import java.util.stream.Stream;

public class DataStream extends Slurper<Integer> {

    int i=0;
    public DataStream() {
        super(null);
    }

    @Override
    protected Stream<String> stream(Path path) {
        return super.stream(path).flatMap(DataStream::chars);
    }

    private static Stream<String> chars(String line) {
        return line.chars().mapToObj(String::valueOf);
    }

    @Override
    public Integer build(String line) {
        if("p".equals(line)) {
            return i;
        }
        i++;
        return null;
    }
}
