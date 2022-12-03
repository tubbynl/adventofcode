package nl.tubby.aoc;

import java.util.List;
import java.util.stream.Collectors;

public record Elf(int nr,List<FoodItem> inventory){
    int sumCalories() {
        return inventory().stream().collect(Collectors.summingInt(FoodItem::calories));
    }
}

record FoodItem(int calories){}