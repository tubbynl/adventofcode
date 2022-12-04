package nl.tubby.aoc;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Stream;

public class ElfFactory extends Slurper<Elf> {
    private int currentElfNr = 1;
    private int currentElfCalories = 0;

    public ElfFactory() {
        super(null);
    }

    @Override
    protected Stream<String> stream(Path path) {
        return Stream.concat(super.stream(path),Stream.of(""));
    }

    @Override
    protected Elf build(String foodStr) {
        Optional<Integer> calories = parseCalories(foodStr);
        if(calories.isPresent()) {
            this.currentElfCalories+=calories.get();
            return null;
        }
        Elf elf = new Elf(currentElfNr++,this.currentElfCalories);
        this.currentElfCalories = 0;
        return elf;
    }

    private Optional<Integer> parseCalories(String food) {
        return Optional.ofNullable(food)
                .map(StringUtils::trimToNull)
                .map(NumberUtils::createInteger);
    }
}

record Elf(int nr,int sumCalories){
}
