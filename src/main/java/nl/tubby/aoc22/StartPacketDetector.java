package nl.tubby.aoc22;

import org.apache.commons.lang3.StringUtils;

import java.util.stream.IntStream;

record StartPacketDetector(int startPacketLength) {
    public Integer detect(String line) {
        var result = IntStream.range(0,line.length()-this.startPacketLength)
                .filter(i -> partialHasAllDifferentChars(i,line))
                .findFirst()
                .getAsInt();
        //because location of "detection" is at the end of the partial
        return result+this.startPacketLength;
    }

    private boolean partialHasAllDifferentChars(int start,String line) {
        String partial = StringUtils.substring(line,start,start+this.startPacketLength);
        return this.startPacketLength==partial.chars().distinct().count();
    }
}
