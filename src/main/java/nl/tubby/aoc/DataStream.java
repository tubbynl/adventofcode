package nl.tubby.aoc;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class DataStream extends Slurper<Integer> {

    private final String singleLine;
    private final int startPacketLength;
    int i=0;
    List<String> currentChars = new ArrayList<>();
    public DataStream(String singleLine, int startPacketLength) {
        super(null);
        this.startPacketLength = startPacketLength;
        this.singleLine = singleLine;
    }

    @Override
    protected Stream<String> stream(Path path) {
        if(this.singleLine!=null) {
            return chars(this.singleLine);
        }
        return super.stream(path).flatMap(DataStream::chars);
    }

    private static Stream<String> chars(String line) {
        return line.chars().mapToObj(i -> String.valueOf((char)i));
    }

    @Override
    public Integer build(String line) {
        i++;
        this.currentChars.add(line);
        if(this.currentChars.size()>this.startPacketLength) {
            this.currentChars.remove(0);
        }
        if(this.currentChars.stream().distinct().count()==this.startPacketLength) {
            return i;
        }
        return null;
    }
}
