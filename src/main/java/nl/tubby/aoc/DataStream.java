package nl.tubby.aoc;

import org.apache.commons.lang3.StringUtils;

import java.util.stream.IntStream;

public class DataStream extends Slurper<Integer> {
    private final int startPacketLength;

    public DataStream(int startPacketLength) {
        super(null);
        this.startPacketLength = startPacketLength;
    }

    @Override
    public Integer build(String line) {
        var result = IntStream.range(0,line.length()-this.startPacketLength)
                .filter(i -> partialHasAllDifferentChars(i,line))
                .findFirst()
                .getAsInt();
        //because location of "detection" is at the end of the partial
        return result+this.startPacketLength;
    }

    boolean partialHasAllDifferentChars(int start,String line) {
        String partial = StringUtils.substring(line,start,start+this.startPacketLength);
        return this.startPacketLength==partial.chars().distinct().count();
    }
}
