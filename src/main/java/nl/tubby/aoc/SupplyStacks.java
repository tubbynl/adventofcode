package nl.tubby.aoc;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

record Ship(List<SupplyStack> stacks) {
    static final Pattern STACK_NRS = Pattern.compile("[0-9\\s]*");
    static boolean isStackNrLine(String line) {
        return line!=null && STACK_NRS.matcher(line).matches();
    }
}
record SupplyStack(List<Character> crates) {
}

record MoveInstruction(int amount,int from,int to) {
    static MoveInstruction parse(String line) {
        int[] ints = Stream.of(StringUtils.split(line,' '))
                .map(StringUtils::trimToNull)
                .filter(NumberUtils::isDigits)
                .mapToInt(NumberUtils::toInt)
                .toArray();
        return new MoveInstruction(ints[0],ints[1],ints[2]);
    }
}
record Context(Ship ship,List<MoveInstruction> instructions) {

}

