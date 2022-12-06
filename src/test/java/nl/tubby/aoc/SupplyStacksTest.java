package nl.tubby.aoc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SupplyStacksTest {

    @ParameterizedTest
    @CsvSource({
            "move 1 from 2 to 1,1,2,1",
            "move 3 from 1 to 3,3,1,3",
            "move 2 from 2 to 1,2,2,1",
            "move 1 from 1 to 2,1,1,2",
    })
    void parseMoveInstruction(String line, int amount, int from, int to) {
        var instruction = MoveInstruction.parse(line);

        assertEquals(amount, instruction.amount());
        assertEquals(from, instruction.from());
        assertEquals(to, instruction.to());
    }

    @Test
    void parseShip() {
        var lines = List.of(
                "    [D]    ",
                "[N] [C]    ",
                "[Z] [M] [P]"
        );

        var ship = Ship.build(3,lines);

        assertEquals(3,ship.stacks().size());
        assertEquals("ZN", ship.stacks().get(0).toString());
        assertEquals("MCD", ship.stacks().get(1).toString());
        assertEquals("P", ship.stacks().get(2).toString());
        assertEquals("NDP",ship.topCrates());

    }

    @ParameterizedTest
    @CsvSource({
            "[D]        ,0",
            "[N] [C]    ,0",
            "[Z] [M] [P],0",
            " 1   2   3 ,3",
            "       ,0",
            " move 1 from 2 to 1,2",
    })
    void isStackNrLine(String line,int expected) {
        int parsed = ContextParser.parseStackCount(line);
        assertEquals(expected,parsed);
    }

    @ParameterizedTest
    @CsvSource({
            "puzzle-example-day5.txt,3,4"
    })
    void parseInput(String file,int expectedStacks,int expectedInstructionCount) {
        var context = new ContextParser()
                .first(Path.of(file));

        assertNotNull(context);
        assertEquals(expectedStacks,context.ship().stacks().size());
        assertEquals(expectedInstructionCount,context.instructions().size());
    }

    @ParameterizedTest
    @CsvSource({
            "puzzle-example-day5.txt,CMZ",
            "puzzle-input-day5.txt,TDCHVHJTG"// <-- solution part 1
    })
    void apply(String file,String topCrates) {
        var context = new ContextParser()
                .first(Path.of(file));

        context.instructions().forEach(context.ship()::applyCrateMover9000);

        assertEquals(topCrates,context.ship().topCrates());
    }

    @Test
    void move() {
        var ship = new Ship(List.of(
                new SupplyStack(new ArrayList<>(List.of('A','B','C'))),
                new SupplyStack(new ArrayList<>(List.of('B')))
        ));

        ship.move(1,2,2);

        assertEquals("A",ship.stacks().get(0).toString());
        assertEquals("BBC",ship.stacks().get(1).toString());
    }

    @ParameterizedTest
    @CsvSource({
            "puzzle-example-day5.txt,MCD",
            "puzzle-input-day5.txt,NGCMPJLHV", // <-- solution part
            "aoc_2022_day05_large_input-2.txt,NGCMPJLHV"
    })
    void apply9001(String file,String topCrates) {
        var context = new ContextParser()
                .first(Path.of(file));

        context.instructions().forEach(context.ship()::applyCrateMover9001);

        assertEquals(topCrates,context.ship().topCrates());
    }
}