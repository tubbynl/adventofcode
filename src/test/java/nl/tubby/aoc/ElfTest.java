package nl.tubby.aoc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElfTest {

    @Test
    void findElveWithMostFoodItems() {

        FoodItemSlurper slurper = new FoodItemSlurper("src/test/resources","example.txt");

        assertEquals(14,slurper.slurp().count());
    }
}