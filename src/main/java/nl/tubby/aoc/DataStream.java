package nl.tubby.aoc;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class DataStream extends Slurper<Integer> {
    private final int startPacketLength;
    int i=0;
    List<String> currentChars = new ArrayList<>();
    public DataStream(int startPacketLength) {
        super(null);
        this.startPacketLength = startPacketLength;
    }

    @Override
    protected Stream<String> stream(Path path) {
        return super.stream(path).flatMap(DataStream::chars);
    }

    protected static Stream<String> chars(String line) {
        return line.chars().mapToObj(i -> String.valueOf((char)i));
    }

    @Override
    public Integer build(String singleChar) {
        i++;
        this.currentChars.add(singleChar);
        if(this.currentChars.size()>this.startPacketLength) {
            this.currentChars.remove(0);
        }
        long countDifferentChars = this.currentChars.stream().distinct().count();
        if(this.startPacketLength==countDifferentChars) {
            return i;
        }
        return null;
    }
}
