package nl.tubby.aoc;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FoodItemSlurper extends Slurper<List<FoodItem>> {
    public FoodItemSlurper(String path, String fileName) {
        super(path,fileName);
    }

    public Stream<List<FoodItem>> slurp() {
        List<String> lines = stream().collect(Collectors.toList());
        String csv = StringUtils.join(lines,",").replace(",,"," ");
        return Stream.of(StringUtils.split(csv," "))
                .map(FoodItemSlurper::createFoodItems);
    }

    private static List<FoodItem> createFoodItems(String calories) {
        return Stream.of(StringUtils.split(calories,","))
                .map(NumberUtils::createInteger)
                .map(FoodItem::new)
                .collect(Collectors.toList());
    }
}
