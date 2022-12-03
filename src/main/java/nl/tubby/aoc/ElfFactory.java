package nl.tubby.aoc;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ElfFactory extends Slurper<Elf> {
    private int currentElfNr = 1;
    private int currentElfCalories = 0;

    public ElfFactory(String path, String file) {
        super(path,file);
    }

    public List<Elf> build() {
        return Stream.concat(stream(),Stream.of(""))
                .flatMap(this::buildElf)
                .collect(Collectors.toList());
    }

    private Stream<Elf> buildElf(String foodStr) {
        Optional<Integer> calories = parseCalories(foodStr);
        if(calories.isPresent()) {
            this.currentElfCalories+=calories.get();
            return Stream.empty();
        }
        Elf elf = new Elf(currentElfNr++,this.currentElfCalories);
        this.currentElfCalories = 0;
        return Stream.of(elf);
    }

    private Optional<Integer> parseCalories(String food) {
        return Optional.ofNullable(food)
                .map(StringUtils::trimToNull)
                .map(NumberUtils::createInteger);
    }
}
