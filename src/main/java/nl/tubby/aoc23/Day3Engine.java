package nl.tubby.aoc23;

import java.util.*;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day3Engine {
    record EngineScematicRow(Map<Integer,Integer> parts, Set<Integer> symbols) {
        private static final Pattern PART = Pattern.compile("([0-9]+)+");
        private static final Pattern SYMBOL = Pattern.compile("([\\W&&[^.]])+");
        static EngineScematicRow parse(String row) {
            var parts = PART.matcher(row).results()
                    .collect(Collectors.toMap(MatchResult::start, r -> Integer.parseInt(r.group())));
            var symbols = SYMBOL.matcher(row).results()
                    .map(MatchResult::start)
                    .collect(Collectors.toUnmodifiableSet());
            return new EngineScematicRow(parts, symbols);
        }

        List<Integer> partsAdjecentToSymbols(EngineScematicRow... rows) {
            return partsAdjecentToSymbols(mergeSymbols(rows));
        }

        List<Integer> partsAdjecentToSymbols(final Set<Integer> symbolPositions) {
            return parts().entrySet()
                    .stream()
                    .filter(e -> hasAdjecentSymbols(e.getKey(),e.getValue(),symbolPositions))
                    .map(Map.Entry::getValue)
                    .toList();
        }
    }

    static Set<Integer> mergeSymbols(EngineScematicRow... rows) {
        return Stream.of(rows)
                .filter(Objects::nonNull)
                .map(EngineScematicRow::symbols)
                .flatMap(Collection::stream)
                .collect(Collectors.toUnmodifiableSet());
    }

    static boolean hasAdjecentSymbols(Integer start,Integer partNr,Set<Integer> symbolPositions) {
        return partPositions(start,partNr).stream()
                .anyMatch(symbolPositions::contains);
    }

    static Set<Integer> partPositions(Integer start,Integer partNr) {
        int size = partNr.toString().length();
        var result = new HashSet<Integer>();
        for(int i=0;i<size;i++) {
            result.add(i+start);
        }
        if(start>0) {
            result.add(start-1);
        }
        result.add(start+size);
        return result;
    }
}
