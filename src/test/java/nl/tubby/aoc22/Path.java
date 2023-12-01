package nl.tubby.aoc22;

public class Path {
    private static final java.nio.file.Path TEST_RESOURCES = java.nio.file.Path.of("src/test/resources");

    public static final java.nio.file.Path of(String file) {
        return TEST_RESOURCES.resolve(file);
    }
}
