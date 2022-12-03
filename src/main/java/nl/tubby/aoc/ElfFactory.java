package nl.tubby.aoc;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ElfFactory {
    private static final Optional<FoodItem> EMPTY = Optional.empty();
    private final FoodItemSlurper slurper;
    private int currentElfNr = 1;

    public ElfFactory(FoodItemSlurper slurper) {
        this.slurper = slurper;
    }

    public List<Elf> build() {
        return this.slurper.slurp()
                .map(this::buildElf)
                .collect(Collectors.toList());
    }

    private Elf buildElf(List<FoodItem> foodItems) {
        return new Elf(currentElfNr++,foodItems);
    }
}
