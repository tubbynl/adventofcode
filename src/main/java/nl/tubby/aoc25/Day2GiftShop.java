package nl.tubby.aoc25;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day2GiftShop {

    static Stream<IdRange> parse(String input) {
        return Stream.of(input.split(","))
                        .map(IdRange::parse);
    }


    record IdRange(int start, int end) {
        static IdRange parse(String pair) {
            var splitted = pair.split("-",2);
            return new IdRange(Integer.parseInt(splitted[0]),Integer.parseInt(splitted[1]));
        }

        Stream<Integer> invalidIds() {
            return IntStream.range(this.start,this.end+1)
                    .boxed()
                    .map(Object::toString)
                    .filter(id -> id.length()%2==0)
                    .filter(id -> id.substring(0,id.length()/2).equals(id.substring(id.length()/2)))
                    .map(Integer::parseInt);
        }
    }
}
