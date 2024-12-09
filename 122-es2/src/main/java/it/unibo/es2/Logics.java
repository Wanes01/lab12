package it.unibo.es2;

public interface Logics {
    void hit(Pair<Integer, Integer> pos);

    boolean isVisible(Pair<Integer, Integer> pos);

    boolean mustQuit();
}
