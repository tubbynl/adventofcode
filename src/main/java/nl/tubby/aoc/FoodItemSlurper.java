package nl.tubby.aoc;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
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

    public Stream<Optional<FoodItem>> slurp() {
        return stream()
                .map(FoodItemSlurper::createFoodItem);
    }

    private static Optional<FoodItem> createFoodItem(String calories) {
        return Optional.ofNullable(calories)
                .map(StringUtils::trimToNull)
                .map(NumberUtils::createInteger)
                .map(FoodItem::new);
    }
}
