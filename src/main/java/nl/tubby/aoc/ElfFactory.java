package nl.tubby.aoc;

import java.util.Collections;
import java.util.List;

public class ElfFactory {
    private final FoodItemSlurper slurper;

    public ElfFactory(FoodItemSlurper slurper) {
        this.slurper = slurper;
    }

    public List<Elf> build() {
        return Collections.emptyList();
    }
}
