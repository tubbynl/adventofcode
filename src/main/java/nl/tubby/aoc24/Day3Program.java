package nl.tubby.aoc24;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class Day3Program {
    static Pattern MUL = Pattern.compile("mul\\((\\d+),(\\d+)\\)");

    public record Instructions(List<Multiply> muls) {
        int sum() {
            return muls().stream().mapToInt(Multiply::exec).sum();
        }
    }


    record Multiply(int a, int b) {
        private Multiply(String a, String b) {
            this(NumberUtils.toInt(a),NumberUtils.toInt(b));
        }

        private Multiply(MatchResult matchResult) {
            this(matchResult.group(1),matchResult.group(2));
        }

        int exec() {
            return a()*b();
        }
    }

    public static Instructions parse(String line) {
        var muls = MUL.matcher(line)
                .results()
                .map(Multiply::new)
                .toList();
        return new Instructions(muls);
    }
}
