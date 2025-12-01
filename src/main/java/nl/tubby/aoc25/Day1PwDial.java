package nl.tubby.aoc25;

import nl.tubby.aoc24.Day1LocationLinker;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Arrays;

public class Day1PwDial {
    private int pos;

    public Day1PwDial(int pos) {
        this.pos = pos;
    }

    int turn(String string) {
        System.out.println(string);
        return pos;
    }

    public int getPos() {
        return pos;
    }
}
