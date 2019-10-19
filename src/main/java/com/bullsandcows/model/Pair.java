package com.bullsandcows.model;

public class Pair<A, B> {
    private final A first;
    private final B second;

    public Pair(A var1, B var2) {
        this.first = var1;
        this.second = var2;
    }

    public A getFirst() {
        return first;
    }

    public B getSecond() {
        return second;
    }
}
