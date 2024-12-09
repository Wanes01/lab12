package it.unibo.es3;

public interface Logics {
    void computeRandomCells(int quantity);

    void expand();

    boolean mustQuit();

    boolean isActive(Pair<Integer, Integer> pos);
}
