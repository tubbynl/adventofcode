package nl.tubby.aoc22;

import nl.tubby.Resource;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

class ClockCircuit {
    private int x = 1;
    private int cycle = 0;

    Integer getSignalStrength(String line) {
        this.cycle++;
        Instruction instruction = Instruction.parse(line);
        boolean hasInstruction = instruction!=null;
        if(hasInstruction) {
            this.x += instruction.value();
        }
        if(shouldMultiplyByX(this.cycle)) {
            return this.cycle*this.x;
        }
        return null;
    }

    static int slurp(Resource resource) {
        var circuit = new ClockCircuit();
        var slurper = new Slurper<>(circuit::getSignalStrength);
        return slurper.sum(resource,Integer::intValue);
    }

    static boolean shouldMultiplyByX(int cycle) {
        return (cycle-20)%40==0;
    }
}

record Instruction(int value) {
    static Instruction parse(String line) {
        if(line.startsWith("addx")) {
            return new Instruction(NumberUtils.toInt(StringUtils.substringAfter(line," ")));
        }
        return null;
    }
}
