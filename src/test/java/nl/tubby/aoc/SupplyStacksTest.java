package nl.tubby.aoc;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
            "puzzle-input-day5.txt,3,4"
    })
    void parseInput(String file,int expectedStacks,int expectedInstructionCount) {
        var parser = new ContextParser();

        var context = parser.slurp(Path.of(file)).findFirst();

        assertTrue(context.isPresent());
        assertEquals(expectedStacks,context.get().ship().stacks().size());
        assertEquals(expectedInstructionCount,context.get().instructions().size());

    }
}