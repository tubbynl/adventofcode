package nl.tubby.aoc25;

import org.apache.commons.lang3.math.NumberUtils;

public class Day1PwDial {
    private int pos;

    public Day1PwDial(int pos) {
        this.pos = pos;
    }

    int turn(String string) {
        int steps = NumberUtils.toInt(string.substring(1));
        if ('L'==string.charAt(0)) { // turn left
            this.pos -= steps;
            if(this.pos<0) {
                this.pos = 99 + this.pos + 1;
            }
        } else { // turn right
            this.pos += steps;
            if(this.pos>99) {
                this.pos = this.pos - 99 - 1;
            }
        }
        //System.out.println(string+" -> "+this.pos);
        return this.pos;
    }

    public int getPos() {
        return pos;
    }
}
