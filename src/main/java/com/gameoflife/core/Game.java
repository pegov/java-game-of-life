package com.gameoflife.core;

import java.awt.Point;

import java.util.HashMap;
import java.util.Random;

public class Game {
    private static final int DEFAULT_WIDTH = 79;
    private static final int DEFAULT_HEIGHT = 30;

    private static final char ALIVE = 'O';
    private static final char DEAD = ' ';

    private final int width;
    private final int height;

    public Game() {
        this(Game.DEFAULT_WIDTH, Game.DEFAULT_HEIGHT);
    }

    public Game(int x) {
        this(x, x);
    }

    public Game(int x, int y) {
        this.width = x;
        this.height = y;
    }

    public void run() throws InterruptedException {
        HashMap<Point, Boolean> nextCells = new HashMap<>();

        Random rand = new Random();
        StringBuilder board = new StringBuilder();

        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                Point cell = new Point(x, y);
                nextCells.put(cell, rand.nextBoolean());
            }
        }
        while (true) {
            String s = new String(new char[60]).replace("\0", "\n");
            String border = new String(new char[this.width]).replace("\0", "-");
            System.out.println(s);

            HashMap<Point, Boolean> cells = new HashMap<>(nextCells);

            board.append(border);
            board.append("\n");

            for (int y = 0; y < this.height; y++) {
                for (int x = 0; x < this.width; x++) {
                    Boolean b = cells.get(new Point(x, y));
                    board.append(b ? Game.ALIVE : Game.DEAD);
                }
                board.append('\n');
            }
            board.append(border);

            for (int x = 0; x < this.width; x++) {
                for (int y = 0; y < this.height; y++) {

                    int left = (x - 1);
                    int right = (x + 1);
                    int up = (y - 1);
                    int down = (y + 1);

                    int n = 0;

                    if (cells.getOrDefault(new Point(left, up), Boolean.FALSE)) {
                        n++;
                    }
                    if (cells.getOrDefault(new Point(x, up), Boolean.FALSE)) {
                        n++;
                    }
                    if (cells.getOrDefault(new Point(right, up), Boolean.FALSE)) {
                        n++;
                    }
                    if (cells.getOrDefault(new Point(left, y), Boolean.FALSE)) {
                        n++;
                    }
                    if (cells.getOrDefault(new Point(right, y), Boolean.FALSE)) {
                        n++;
                    }
                    if (cells.getOrDefault(new Point(left, down), Boolean.FALSE)) {
                        n++;
                    }
                    if (cells.getOrDefault(new Point(x, down), Boolean.FALSE)) {
                        n++;
                    }
                    if (cells.getOrDefault(new Point(right, down), Boolean.FALSE)) {
                        n++;
                    }

                    Point cell = new Point(x, y);
                    if (cells.get(cell) && (n == 2 || n == 3)) {
                        nextCells.put(cell, Boolean.TRUE);
                    } else if (!cells.get(cell) && n == 3) {
                        nextCells.put(cell, Boolean.TRUE);
                    } else {
                        nextCells.put(cell, Boolean.FALSE);
                    }
                }
            }
            System.out.println(board.toString());
            board.setLength(0);

            Thread.sleep(250);
        }
    }

}
