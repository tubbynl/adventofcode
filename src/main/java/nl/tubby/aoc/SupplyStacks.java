package nl.tubby.aoc;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

record Ship(List<SupplyStack> stacks) {

    static Ship build(Stream<String> lines) {
        var builder = new ShipYard();
        lines
                .map(builder::add)
                .mapToInt(Integer::intValue)
                .sum();
        return builder.build();
    }

    static Ship slurp(Path path) {
        var slurper = new Slurper<>(s->s, Optional.empty(), s -> s.contains("["));
        return build(slurper.slurp(path));
    }

    void applyCrateMover9000(MoveInstruction instruction) {
        IntStream
                .range(0,instruction.amount())
                .forEach(i -> move(instruction.from(),instruction.to(),1));
    }

    void applyCrateMover9001(MoveInstruction instruction) {
        move(instruction.from(), instruction.to(), instruction.amount());
    }

    SupplyStack stack(int col) {
        return stacks().get(col-1);
    }

    void move(int from, int to,int amount) {
        var crate = stack(from).pickup(amount);
        stack(to).place(crate);
    }

    public String topCrates() {
        return stacks().stream()
                .map(SupplyStack::top)
                .map(c -> c.toString())
                .collect(Collectors.joining());
    }
}

class ShipYard {
    private final List<SupplyStack> stacks = new ArrayList<>();

    Ship build() {
        return new Ship(stacks.stream()
                .map(SupplyStack::reverse)
                .toList());
    }

    Integer add(String lineOfCrates) {
        int stackCount = Math.round(lineOfCrates.length()/4);
        while(this.stacks.size()<=stackCount) {
            this.stacks.add(new SupplyStack(new ArrayList<>()));
        }
        return IntStream.range(0,stackCount+1)
                .map(col -> charAt(col+1,lineOfCrates)
                    .map(crate -> {
                        this.stacks.get(col).push(crate);
                        return 1;
                    }).orElse(0)).sum();
    }

    private static Optional<Character> charAt(int col,String line) {
        int colIndex = 1+((col-1)*4);
        return Optional.ofNullable(line)
                .filter(l -> l.length()>colIndex)
                .map(l -> line.charAt(colIndex))
                .filter(Objects::nonNull)
                .filter(Character::isUpperCase);
    }
}

record SupplyStack(List<Character> crates) {
    void push(Character crate) {
        this.crates.add(crate);
    }

    Character top() {
        if(crates().isEmpty()) {
            return Character.valueOf(' ');
        }
        return crates().get(crates().size()-1);
    }

    List<Character> pickup(int amount) {
        List<Character> result = IntStream.range(0,amount)
                .mapToObj(i -> crates().remove(crates().size()-1))
                .collect(Collectors.toList());
        Collections.reverse(result);
        return result;
    }

    void place(List<Character> crates) {
        crates().addAll(crates);
    }

    SupplyStack reverse() {
        List<Character> crates = new ArrayList<>(crates());
        Collections.reverse(crates);
        return new SupplyStack(crates);
    }

    public String toString() {
        return crates().stream()
                .map(Object::toString)
                .collect(Collectors.joining());
    }
}

record MoveInstruction(int amount,int from,int to) {
    static MoveInstruction parse(String line) {
        if(!StringUtils.startsWith(line,"move ")) {
            return null;
        }
        int[] ints = parseAsInts(line).toArray();
        return new MoveInstruction(ints[0],ints[1],ints[2]);
    }

    static IntStream parseAsInts(String line) {
        return line==null?IntStream.empty():Stream.of(StringUtils.split(line,' '))
                .map(StringUtils::trimToNull)
                .filter(NumberUtils::isDigits)
                .mapToInt(NumberUtils::toInt);
    }

    static List<MoveInstruction> slurp(final Path path) {
        var slurper = new Slurper<>(MoveInstruction::parse);
        return slurper.list(path);
    }
}
record Context(Ship ship,List<MoveInstruction> instructions) {
    static Context build(Path path) {
        var ship = CompletableFuture.supplyAsync(() -> Ship.slurp(path));
        var instructions = CompletableFuture.supplyAsync(() -> MoveInstruction.slurp(path));
        return new Context(ship.join(),instructions.join());
    }
}

