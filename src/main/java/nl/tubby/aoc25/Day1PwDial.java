package nl.tubby.aoc25;

import org.apache.commons.lang3.math.NumberUtils;

public class Day1PwDial {
    private int pos;
    private int zeroPassed = 0;
    private int zeros = 0;

    public Day1PwDial(int pos) {
        this.pos = pos;
    }

    int turn(String string) {
        //System.out.print(this.pos+"+");
        boolean atZero = this.pos==0;
        int stepsInput = NumberUtils.toInt(string.substring(1));
        int steps = stepsInput % 100;
        int zerosPassed = 0;
        if ('L'==string.charAt(0)) { // turn left
            this.pos -= steps;
            if(this.pos<0) {
                this.pos = 99 + this.pos + 1;
                if(!atZero && this.pos!=0) {
                    zerosPassed++;
                }
            }
        } else { // turn right
            this.pos += steps;
            if(this.pos>99) {
                this.pos = this.pos - 99 - 1;
                if(!atZero && this.pos!=0) {
                    zerosPassed++;
                }
            }
        }

        if(this.pos==0) {
            this.zeros++;
        }

        if(stepsInput>=100) {
            zerosPassed += stepsInput / 100;
        }
        //System.out.println(string+" -> "+this.pos+": "+zerosPassed);
        this.zeroPassed += zerosPassed;
        return this.pos;
    }

    public int getPos() {
        return pos;
    }

    public int getZeros() {
        return zeros;
    }

    public int getZeroPassed() {
        return zeroPassed;
    }
}
