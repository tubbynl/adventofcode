package nl.tubby.aoc23;

import nl.tubby.aoc22.Path;
import nl.tubby.aoc22.Slurper;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class Day3EngineTest {

    @CsvSource({
            "467..114..,2,0,114,0",
            "..35..633.,2,2,35,0",
            ".....+.58.,1,7,58,1",
            "...$.*....,0,-1,-1,2"
    })
    @ParameterizedTest
    void parseRow(String row,int partCount,int firstPartIndex,int expectedMinPartNr,int symbolCount) {
        var sut = Day3Engine.EngineScematicRow.parse(row);

        assertEquals(partCount,sut.parts().size());
        // check parts
        var firstPart = sut.parts().keySet().stream().mapToInt(Integer::intValue).min();
        var minPartNr = sut.parts().values().stream().mapToInt(Integer::intValue).min();
        if(partCount>0) {
            assertEquals(firstPartIndex,firstPart.orElse(-1));
            assertEquals(expectedMinPartNr,minPartNr.orElse(-1));
        } else {
            assertTrue(firstPart.isEmpty());
            assertTrue(minPartNr.isEmpty());
        }

        // check symbols
        assertEquals(symbolCount,sut.symbols().size());
    }

    @Test
    void mergeSymbols() {
        var map = Collections.<Integer,Integer>emptyMap();

        assertEquals(Set.of(3,4),Day3Engine.mergeSymbols(null,new Day3Engine.EngineScematicRow(map,Set.of(3,4))));
        assertEquals(Set.of(1,4),Day3Engine.mergeSymbols(new Day3Engine.EngineScematicRow(map,Set.of(1,4)),null));
        assertEquals(Set.of(1,3,4),Day3Engine.mergeSymbols(new Day3Engine.EngineScematicRow(map,Set.of(1,4)),new Day3Engine.EngineScematicRow(map,Set.of(3))));
        assertEquals(Set.of(),Day3Engine.mergeSymbols(null,null));

    }

    @CsvSource({
            "0,1337,0 1 2 3 4",
            "1,1337,0 1 2 3 4 5",
            "5,1337,4 5 6 7 8 9"
    })
    @ParameterizedTest
    void partPositions(int start,int part,String positions) {
        var expectedPositions = Stream.of(StringUtils.split(positions)).map(Integer::valueOf).collect(Collectors.toUnmodifiableSet());

        assertEquals(expectedPositions,Day3Engine.partPositions(start,part));
    }

    @ParameterizedTest
    @CsvSource({
            "aoc-2023-day3-example.txt,4361",
            "aoc-2023-day3-input.txt,410371" //FIXME: That's not the right answer; your answer is too low.
    })
    void puzzlePart1(String file,int partsSum) {
        var slurper = new Slurper<>(Day3Engine.EngineScematicRow::parse);

        var rows = slurper.list(Path.of(file));

        var parts = new ArrayList<Integer>();
        for (int i=0;i<rows.size();i++) {
            Day3Engine.EngineScematicRow previous = i>0?rows.get(i-1):null;
            Day3Engine.EngineScematicRow current = rows.get(i);
            Day3Engine.EngineScematicRow next = i<(rows.size()-1)?rows.get(i+1):null;
            var partsCurrentRow = current.partsAdjecentToSymbols(previous,current,next);
            parts.addAll(partsCurrentRow);
        }

        assertEquals(partsSum,parts.stream().mapToInt(Integer::intValue).sum());
    }
}