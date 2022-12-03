package nl.tubby.aoc;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FoodItemSlurper {
    private final Path path;


    public FoodItemSlurper(String path, String fileName) {
        this.path = Path.of(path,fileName);
    }

    private Stream<String> stream() {
        try {
            return Files.readAllLines(this.path)
                    .stream();
        } catch (IOException e) {
            System.err.println("unable to open "+this.path+": "+e.getMessage());
            return Stream.empty();
        }
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
