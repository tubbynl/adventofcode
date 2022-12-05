package nl.tubby.aoc;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.nio.file.Path;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

record Ship(List<SupplyStack> stacks) {

    static Ship build(List<String> lines) {
        return new Ship(Collections.emptyList());
    }
}
record SupplyStack(List<Character> crates) {
}

record MoveInstruction(int amount,int from,int to) {
    static MoveInstruction parse(String line) {
        int[] ints = ContextParser.parseAsInts(line)
                .toArray();
        if(ints.length<3) {
            return null;
        }
        return new MoveInstruction(ints[0],ints[1],ints[2]);
    }
}
record Context(Ship ship,List<MoveInstruction> instructions) {


}

class ContextParser extends Slurper<Context> {
    static IntStream parseAsInts(String line) {
        return line==null?IntStream.empty():Stream.of(StringUtils.split(line,' '))
                .map(StringUtils::trimToNull)
                .filter(NumberUtils::isDigits)
                .mapToInt(NumberUtils::toInt);
    }

    static int parseStackCount(String line) {
        return parseAsInts(line).max().orElse(0);
    }
    private List<String> stackLines = new ArrayList<>();
    private List<MoveInstruction> instructions = new ArrayList<>();
    private Integer stackCount = null;

    public ContextParser() {
        super(null);
    }

    @Override
    protected Stream<String> stream(Path path) {
        return Stream.concat(super.stream(path),Stream.of(""));
    }

    @Override
    protected Context build(String line) {
        if(stackCount==null) {
            int optStackCount = parseStackCount(line);
            if(optStackCount>0) {
                stackCount = optStackCount;
            } else {
                this.stackLines.add(line);
            }
            return null;
        }
        MoveInstruction move = MoveInstruction.parse(line);
        if(move!=null) {
            this.instructions.add(move);
            return null;
        } else if(this.instructions.isEmpty()) {
            return null; // handle empty line before instructions
        }
        // EOF
        return new Context(Ship.build(this.stackLines),this.instructions);
    }
}

