package nl.tubby.aoc25;

import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Day2GiftShop {

    static Stream<IdRange> parse(String input) {
        return Stream.of(input.split(","))
                        .map(IdRange::parse);
    }


    record IdRange(long start, long end) {
        static IdRange parse(String pair) {
            var splitted = pair.split("-",2);
            return new IdRange(Long.parseLong(splitted[0]),Long.parseLong(splitted[1]));
        }

        Stream<Long> invalidIds(Predicate<String> filter) {
            return LongStream.range(this.start,this.end+1)
                    .boxed()
                    .map(Object::toString)
                    .filter(filter)
                    .map(Long::parseLong);
        }

        static boolean idCheck1(String id) {
            return id.length()%2==0 && id.substring(0,id.length()/2).equals(id.substring(id.length()/2));
        }

        static boolean idCheck2(String id) {
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
    }
}
