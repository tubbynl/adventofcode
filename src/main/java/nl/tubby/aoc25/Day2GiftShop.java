package nl.tubby.aoc25;

import nl.tubby.Resource;
import nl.tubby.Slurper;

import java.util.function.Function;
import java.util.function.LongPredicate;
import java.util.function.Predicate;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Day2GiftShop extends Slurper<Stream<Day2GiftShop.IdRange>> {

    public Day2GiftShop() {
        super(Day2GiftShop::parse);
    }

    private static Stream<IdRange> parse(String input) {
        return Stream.of(input.split(","))
                        .map(IdRange::parse);
    }

    Stream<IdRange> flatSlurp(Resource resource) {
        return slurp(resource).flatMap(Function.identity());
    }

    LongStream invalidIds(Resource resource, LongPredicate filter) {
        return flatSlurp(resource)
                .map(idRange -> idRange.invalidIds(filter))
                .flatMapToLong(Function.identity());
    }


    static boolean idCheck1(long longId) {
        String id = ""+longId;
        return id.length()%2==0 && id.substring(0,id.length()/2).equals(id.substring(id.length()/2));
    }

    static boolean idCheck2(long longId) {
        String id = ""+longId;
        int half = id.length()/2;
        for(int i=1;i<=half;i++) {
            String partial = id.substring(0,i);
            if(id.length()%partial.length()==0) {
                int repeat = id.length()/partial.length();
                if(partial.repeat(repeat).equals(id)) {
                    return true;
                }
            }
        }
        return false;
    }

    record IdRange(long start, long end) {
        static IdRange parse(String pair) {
            var splitted = pair.split("-",2);
            return new IdRange(Long.parseLong(splitted[0]),Long.parseLong(splitted[1]));
        }

        LongStream invalidIds(LongPredicate filter) {
            return LongStream.rangeClosed(this.start,this.end)
                    .filter(filter);
        }
    }
}
