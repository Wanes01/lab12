package it.unibo.es2;

import java.util.HashSet;
import java.util.Set;

public class LogicsImpl implements Logics {

    private final Set<Pair<Integer, Integer>> visibles = new HashSet<>();
    private final int size;

    public LogicsImpl(final int size) {
        this.size = size;
    }

    @Override
    public void hit(Pair<Integer, Integer> pos) {
        if (!this.visibles.contains(pos)) {
            this.visibles.add(pos);
        } else {
            this.visibles.remove(pos);
        }
    }

    @Override
    public boolean isVisible(Pair<Integer, Integer> pos) {
        return this.visibles.contains(pos);
    }

    @Override
    public boolean mustQuit() {
        for (int i = 0; i < size; i++) {
            int row = visiblesOn(Side.ROW, i);
            int col = visiblesOn(Side.COL, i);
            if (row == this.size || col == this.size) {
                return true;
            }
        }
        return false;
    }

    private int visiblesOn(Side side, int index) {
        return (int) this.visibles.stream()
                .filter(pos -> side == Side.ROW ? pos.getX() == index : pos.getY() == index)
                .count();
    }

    private enum Side {
        ROW, COL
    }
}
