package nl.tubby.aoc22;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Optional;

public class ElfCaloriesCollector {
    private int currentElfCalories = 0;

    protected Integer collectOrParse(String foodStr) {
        Optional<Integer> calories = parseCalories(foodStr);
        if(calories.isPresent()) {
            this.currentElfCalories+=calories.get();
            return null;
        }
        var result = Integer.valueOf(this.currentElfCalories);
        this.currentElfCalories = 0;
        return result;
    }

    private Optional<Integer> parseCalories(String food) {
        return Optional.ofNullable(food)
                .map(StringUtils::trimToNull)
                .map(NumberUtils::createInteger);
    }
}
