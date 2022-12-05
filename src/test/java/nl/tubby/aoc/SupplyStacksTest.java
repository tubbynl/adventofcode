package nl.tubby.aoc;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class SupplyStacksTest {

    @ParameterizedTest
    @CsvSource({
        "move 1 from 2 to 1,1,2,1",
        "move 3 from 1 to 3,3,1,3",
        "move 2 from 2 to 1,2,2,1",
        "move 1 from 1 to 2,1,1,2",
    })
    void parseMoveInstruction(String line,int amount,int from,int to) {
        var instruction = MoveInstruction.parse(line);

        assertEquals(amount,instruction.amount());
        assertEquals(from,instruction.from());
        assertEquals(to,instruction.to());
    }
}