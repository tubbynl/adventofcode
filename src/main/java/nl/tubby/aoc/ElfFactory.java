package nl.tubby.aoc;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ElfFactory extends Slurper<Elf> {
    private int currentElfNr = 1;
    private List<FoodItem> currentElfInventory = new ArrayList<>();

    public ElfFactory(String path, String file) {
        super(path,file);
    }

    public List<Elf> build() {
        return Stream.concat(stream(),Stream.of(""))
                .flatMap(this::buildElf)
                .collect(Collectors.toList());
    }

    private Stream<Elf> buildElf(String foodStr) {
        Optional<FoodItem> foodItem = buildFoodItem(foodStr);
        if(foodItem.isPresent()) {
            this.currentElfInventory.add(foodItem.get());
            return Stream.empty();
        }
        Elf elf = new Elf(currentElfNr++,this.currentElfInventory);
        this.currentElfInventory = new ArrayList<>();
        return Stream.of(elf);
    }

    private Optional<FoodItem> buildFoodItem(String food) {
        return Optional.ofNullable(food)
                .map(StringUtils::trimToNull)
                .map(NumberUtils::createInteger)
                .map(FoodItem::new);
    }
}
