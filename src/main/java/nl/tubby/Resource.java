package nl.tubby;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public record Resource(String file) {
    public static Resource of(String file) {
        return new Resource(file);
    }

    private Path resolveToPath(Class<?> type) {
        var url = type.getResource(file);
        try {
            return Path.of(url.toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException("unable get path from "+url,e);
        }
    }

    public Stream<String> stream(Class<?> classLoader) {
        var path = resolveToPath(classLoader);
        try {
            return Files.lines(path);
        } catch (IOException e) {
            throw new RuntimeException("unable to stream "+path,e);
        }
    }
}
