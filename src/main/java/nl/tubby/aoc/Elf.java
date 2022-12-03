package nl.tubby.aoc;

import java.util.List;

public record Elf(int nr,List<FoodItem> inventory){}

record FoodItem(int calories){}