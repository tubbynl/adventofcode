package nl.tubby.abel;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class DeWegHalersTest {

    @ParameterizedTest
    @ValueSource(ints = {50,1000,10_000,100_000,1_000_000})
    void runUntilWon(int runs) {
        var result = IntStream.range(0,runs)
                .parallel()
                .mapToObj(i -> new DeWegHalers())
                .mapToInt(DeWegHalers::runUntilWon)
                .average()
                .getAsDouble();

        assertTrue(result>10);
        // range in 65~66ish
        //assertEquals(13.37,result);
    }
}