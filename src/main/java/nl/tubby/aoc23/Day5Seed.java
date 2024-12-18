package nl.tubby.aoc23;

import nl.tubby.Resource;
import nl.tubby.Slurper;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Day5Seed {

    record MapRange(int destinationStart,int sourceStart, int length) {
        static Optional<MapRange> parse(String raw) {
            if(StringUtils.isEmpty(raw) || StringUtils.contains(raw,':')) {
                return Optional.empty();
            }
            var values = Stream.of(StringUtils.split(raw," ",3))
                    .map(NumberUtils::createInteger)
                    .mapToInt(Integer::intValue)
                    .toArray();
            return Optional.of(new MapRange(values[0],values[1],values[2]));
        }

        int map(int input) {
            if (input<sourceStart()) {
                return input;
            } else if(input>(sourceStart()+length()-1)) {
                return input;
            }
            return input-sourceStart+destinationStart;
        }
    }

    static class SeedMappingSlurper extends Slurper<Optional<MapRange>> {
        private String type;
        private List<Integer> currentValues;
        public SeedMappingSlurper() {
            super(MapRange::parse);
        }

        @Override
        protected Optional<MapRange> build(String line) {
            if(StringUtils.contains(line,':')) {
                type = StringUtils.substringBefore(line,':');
                if ("seeds".equals(type)) {
                    var seeds = StringUtils.split(StringUtils.substringAfter(line,':')," ");
                    currentValues = Stream.of(seeds).map(NumberUtils::createInteger).toList();
                }
            } else if(StringUtils.isBlank(line)) {
                System.err.println(type+": "+currentValues);
            }
            return super.build(line);
        }

        public List<Integer> slurpAndMap(Resource path) {
            slurp(path).forEach(mapper -> {
                mapper.ifPresent(m -> this.currentValues = mapValues(m));
            });
            System.err.println(currentValues);
            return this.currentValues;
        }

        List<Integer> mapValues(MapRange mapper) {
            return this.currentValues
                    .stream()
                    .map(mapper::map)
                    .toList();
        }
    }
}
