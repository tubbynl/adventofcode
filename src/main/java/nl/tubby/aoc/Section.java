package nl.tubby.aoc;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
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
        String[] sections = StringUtils.split(line,",");
        return Pair.of(parse(sections[0]),parse(sections[1]));
    }

    private static Section parse(String line) {
        int[] values = Stream.of(StringUtils.split(line,"-"))
                .mapToInt(NumberUtils::toInt).toArray();
        return new Section(values[0],values[1]);
    }
}
