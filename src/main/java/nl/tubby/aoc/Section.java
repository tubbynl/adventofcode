package nl.tubby.aoc;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.stream.Stream;

public record Section(int start, int end) {
    boolean contains(Section section) {
        return start()<= section.start() && end()>=section.end();
    }

    boolean overlaps(Section section) {
        // 5-7,7-9 overlap on 7
        // 5-7,3-5 overlap on 5
        return end()>=section.start() || start()<= section.end();
    }
}

class SectionSlurper extends Slurper<PairOfSections> {

    public SectionSlurper(String path, String fileName) {
        super(path, fileName);
    }

    public Stream<PairOfSections> build() {
        return slurp(SectionSlurper::build);
    }

    protected static PairOfSections build(String line) {
        String[] sections = StringUtils.split(line,",");
        return new PairOfSections(parse(sections[0]),parse(sections[1]));
    }

    private static Section parse(String line) {
        int[] values = Stream.of(StringUtils.split(line,"-"))
                .mapToInt(NumberUtils::toInt).toArray();
        return new Section(values[0],values[1]);
    }
}

record PairOfSections(Section left,Section right) {

    boolean contains() {
        return left().contains(right()) || right().contains(left());
    }

    boolean overlaps() {
        return left().overlaps(right()) || right().overlaps(left());
    }
}
