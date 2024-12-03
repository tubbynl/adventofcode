package nl.tubby.darts;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class DartTest {

    static Stream<Dart> all(int score) {
        return Stream.of(
                Dart.s(score),
                Dart.d(score),
                Dart.t(score));
    }

    List<Dart> all = IntStream.rangeClosed(1,20)
            .mapToObj(score -> Dart.s(score))
            .flatMap(dart -> Stream.of(dart,Dart.d(dart.score()))
            ).toList();

}
