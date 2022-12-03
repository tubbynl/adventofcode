package nl.tubby.aoc;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class FoodItemSlurperTest {

    @Test
    void slurp() {
        FoodItemSlurper slurper = new FoodItemSlurper("src/test/resources", "puzzle-example-day1.txt");

        List<List<FoodItem>> items = slurper.slurp().collect(Collectors.toList());
        assertEquals(5,slurper.slurp().count());

        assertEquals(3,items.get(0).size());
        assertEquals(1,items.get(1).size());
        assertEquals(2,items.get(2).size());
        assertEquals(3,items.get(3).size());
        assertEquals(1,items.get(4).size());
    }
}