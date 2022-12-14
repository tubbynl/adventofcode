package nl.tubby.aoc;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.nio.file.Path;

class ClockCircuit {
    private int x = 1;

    Boolean parseAndApply(String line) {
        Instruction instruction = Instruction.parse(line);
        boolean hasInstruction = instruction!=null;
        if(hasInstruction) {
            this.x += instruction.value();
        }
        return hasInstruction;
    }

    static ClockCircuit slurp(Path path) {
        var circuit = new ClockCircuit();
        var slurper = new Slurper<>(circuit::parseAndApply);
        var count = slurper.slurp(path).count();
        return circuit;
    }

    public int x() {
        return x;
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
