package nl.tubby.aoc;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public record Section(int start, int end) {
    boolean contains(Section section) {
        return set().containsAll(section.set());
    }

    Set<Integer> set() {
        return IntStream.range(start(),end()+1).boxed().collect(Collectors.toSet());
    }

    boolean overlaps(Section section) {
        Set<Integer> otherSectionRange = section.set();
        return set().stream()
                .filter(otherSectionRange::contains)
                .findAny()
                .isPresent();
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
