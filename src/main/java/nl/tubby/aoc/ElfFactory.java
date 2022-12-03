package nl.tubby.aoc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ElfFactory {
    private static final Optional<FoodItem> EMPTY = Optional.empty();
    private final FoodItemSlurper slurper;

    public ElfFactory(FoodItemSlurper slurper) {
        this.slurper = slurper;
    }

    public List<Elf> build() {
        List<Optional<FoodItem>> foodItems = this.slurper.slurp().collect(Collectors.toList());

        int index = 0;
        int elfNr = 1;
        List<Elf> elves = new ArrayList<>();
        while(foodItems.contains(Optional.empty())) {
            int nextIndex = foodItems.indexOf(Optional.empty());
            elves.add(new Elf(elfNr,foodItems.subList(index,nextIndex).stream().map(Optional::get).collect(Collectors.toList())));
            elfNr++;
            index = nextIndex;
        }
        return elves;
    }
}
