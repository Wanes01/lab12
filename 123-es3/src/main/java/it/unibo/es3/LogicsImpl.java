package it.unibo.es3;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class LogicsImpl implements Logics {

    private final Set<Pair<Integer, Integer>> cells = new HashSet<>();
    private final int size;

    public LogicsImpl(final int size) {
        this.size = size;
    }

    @Override
    public void computeRandomCells(int quantity) {
        if (!cells.isEmpty()) {
            throw new IllegalStateException("The starting cells have been already computed");
        }
        final Random r = new Random();
        while (this.cells.size() < quantity) {
            this.cells.add(
                    new Pair<Integer, Integer>(r.nextInt(this.size), r.nextInt(this.size)));
        }
    }

    @Override
    public void expand() {
        if (cells.isEmpty()) {
            throw new IllegalStateException("Random cells must be computed prior expansion");
        }
        final Set<Pair<Integer, Integer>> toAdd = new HashSet<>();
        for (final var cell : this.cells) {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    final Pair<Integer, Integer> pos = new Pair<>(cell.getX() + i, cell.getY() + j);
                    if (isOnBoard(pos)
                            && !this.cells.contains(pos)
                            && !toAdd.contains(pos)) {
                        toAdd.add(pos);
                    }
                }
            }
        }
        this.cells.addAll(toAdd);
    }

    private boolean isOnBoard(Pair<Integer, Integer> cell) {
        return 0 <= cell.getX() && cell.getX() < this.size
                && 0 <= cell.getY() && cell.getY() < this.size;
    }

    @Override
    public boolean mustQuit() {
        return this.cells.size() == this.size * this.size;
    }

    @Override
    public boolean isActive(Pair<Integer, Integer> pos) {
        return this.cells.contains(pos);
    }

}
