package nl.tubby.darts;

record Dart(int score, type type) {
    static Dart s(int score) {
        return new Dart(score, nl.tubby.darts.type.SINGLE);
    }

    static Dart d(int score) {
        return new Dart(score, nl.tubby.darts.type.DOUBLE);
    }

    static Dart t(int score) {
        return new Dart(score, nl.tubby.darts.type.TRIPLE);
    }


}

enum type {
    SINGLE,
    DOUBLE,
    TRIPLE
}

record Turn(Dart first,Dart second, Dart third) {

};