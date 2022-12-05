package nl.tubby.aoc;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

record Ship(List<SupplyStack> stacks) {

    static Ship build(int stackCount,List<String> lines) {
        var reversed = new ArrayList<>(lines);
        Collections.reverse(reversed);
        var stacks = IntStream.range(1,stackCount+1)
                .mapToObj(col -> SupplyStack.build(col,reversed))
                .toList();
        return new Ship(stacks);
    }

    void apply(MoveInstruction instruction) {
        IntStream
                .range(0,instruction.amount())
                .forEach(i -> move(instruction));
    }

    SupplyStack stack(int col) {
        return stacks().get(col-1);
    }

    private void move(MoveInstruction instruction) {
        var crate = stack(instruction.from()).pickup();
        stack(instruction.to()).place(crate);
    }

    public String topCrates() {
        return stacks().stream()
                .map(SupplyStack::top)
                .map(c -> c.toString())
                .collect(Collectors.joining());
    }
}
record SupplyStack(List<Character> crates) {
    static SupplyStack build(final int col,List<String> lines) {
        int colIndex = 1+((col-1)*4);
        return new SupplyStack(lines.stream()
                .map(line -> line.length()<colIndex?null:line.charAt(colIndex))
                .filter(Objects::nonNull)
                .filter(Character::isUpperCase)
                .collect(Collectors.toList()));
    }

    Character top() {
        return crates().get(crates().size()-1);
    }

    Character pickup() {
        return crates().remove(crates().size()-1);
    }

    void place(Character crate) {
        crates().add(crate);
    }

    public String toString() {
        return crates().stream()
                .map(c -> c.toString())
                .collect(Collectors.joining());
    }
}

record MoveInstruction(int amount,int from,int to) {
    static MoveInstruction parse(String line) {
        if(!StringUtils.startsWith(line,"move ")) {
            return null;
        }
        int[] ints = ContextParser.parseAsInts(line).toArray();
        return new MoveInstruction(ints[0],ints[1],ints[2]);
    }
}
record Context(Ship ship,List<MoveInstruction> instructions) {

}

class ContextParser extends Slurper<Context> {
    private static final String EOF = "----";
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
        return Stream.concat(super.stream(path),Stream.of(EOF));
    }

    @Override
    protected Context build(String line) {
        if(this.stackCount==null) {
            int optStackCount = parseStackCount(line);
            if(optStackCount>0) {
                this.stackCount = optStackCount;
            } else {
                this.stackLines.add(line);
            }
        }
        MoveInstruction move = MoveInstruction.parse(line);
        if(move!=null) {
            this.instructions.add(move);
        }
        if(EOF.equals(line)) {
            var ship = Ship.build(this.stackCount,this.stackLines);
            return new Context(ship,this.instructions);
        }
        return null;
    }
}

