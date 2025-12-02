package nl.tubby.aoc25;

import nl.tubby.Resource;
import nl.tubby.Slurper;

import java.util.function.Function;
import java.util.function.LongPredicate;
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
                .map(IdRange::stream)
                .flatMapToLong(Function.identity())
                .filter(filter);
    }


    static boolean idCheck1(long longId) {
        String id = ""+longId;
        return id.length()%2==0 && id.substring(0,id.length()/2).equals(id.substring(id.length()/2));
    }

    static boolean idCheck2(long longId) {
        String id = ""+longId;
        int half = id.length()/2;
        for(int i=1;i<=half;i++) {
            if(id.length()%i==0) {
                int repeat = id.length()/i;
                if(id.substring(0,i).repeat(repeat).equals(id)) {
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

        LongStream stream() {
            return LongStream.rangeClosed(this.start,this.end);
        }
    }
}
