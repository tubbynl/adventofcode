package nl.tubby.aoc23;

import org.apache.commons.lang3.StringUtils;

public class Day1Calibator {

    static Integer parse(String input) {
        var onlyDigits = StringUtils.getDigits(input);

        var first = StringUtils.substring(onlyDigits,0,1);
        var last = StringUtils.substring(onlyDigits,-1);
        return Integer.parseInt(first+last);
    }
}
