package nl.tubby.aoc;

import org.apache.commons.lang3.tuple.Pair;

import java.util.stream.Stream;

public record Section(int start, int end) {
}

class SectionSlurper extends Slurper<Pair<Section,Section>> {

    public SectionSlurper(String path, String fileName) {
        super(path, fileName);
    }

    public Stream<Pair<Section,Section>> build() {
        return slurp(SectionSlurper::build);
    }

    private static Pair<Section,Section> build(String line) {
        return Pair.of(new Section(0,1),new Section(1,2));
    }
}
