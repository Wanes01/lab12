package it.unibo.es3;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class GUI extends JFrame {
    private static final int START_ACTIVES = 3;
    private final Map<JButton, Pair<Integer, Integer>> cells = new HashMap<>();
    private final Logics logic;

    public GUI(int width) {
        this.logic = new LogicsImpl(width);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(70 * width, 70 * width);

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel grid = new JPanel(new GridLayout(width, width));
        JButton next = new JButton(">");
        mainPanel.add(grid, BorderLayout.CENTER);
        mainPanel.add(next, BorderLayout.SOUTH);
        this.getContentPane().add(mainPanel);

        next.addActionListener(e -> {
            this.logic.expand();
            updateCellsVisibility();
            if (this.logic.mustQuit()) {
                System.exit(1);
            }
        });

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                final var pos = new Pair<>(i, j);
                final JButton jb = new JButton();
                this.cells.put(jb, pos);
                grid.add(jb);
            }
        }

        this.logic.computeRandomCells(START_ACTIVES);
        updateCellsVisibility();
        this.setVisible(true);
    }

    private void updateCellsVisibility() {
        this.cells.forEach((jb, pos) -> {
            jb.setText(this.logic.isActive(pos) ? "*" : " ");
        });
    }
}