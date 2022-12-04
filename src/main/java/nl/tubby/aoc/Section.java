package nl.tubby.aoc;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public record Section(int start, int end) {
    static Section parse(String line) {
        int[] values = Stream.of(StringUtils.split(line,"-"))
                .mapToInt(NumberUtils::toInt).toArray();
        return new Section(values[0],values[1]);
    }
    boolean contains(Section section) {
        return set().containsAll(section.set());
    }

    private IntStream stream() {
        return IntStream.range(start(),end()+1);
    }
    protected Set<Integer> set() {
        return stream().boxed().collect(Collectors.toSet());
    }

    boolean overlaps(Section section) {
        Set<Integer> otherSectionRange = section.set();
        return stream()
                .filter(otherSectionRange::contains)
                .findAny()
                .isPresent();
    }
}

class SectionSlurper extends Slurper<PairOfSections> {
    public SectionSlurper() {
        super(PairOfSections::parse);
    }
}

record PairOfSections(Section left,Section right) {

    static PairOfSections parse(String line) {
        String[] sections = StringUtils.split(line,",");
        return new PairOfSections(Section.parse(sections[0]),Section.parse(sections[1]));
    }

    boolean contains() {
        return left().contains(right()) || right().contains(left());
    }

    boolean overlaps() {
        return left().overlaps(right()) || right().overlaps(left());
    }
}
