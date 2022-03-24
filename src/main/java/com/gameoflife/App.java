package com.gameoflife;

import com.gameoflife.core.Game;

public class App {
    public static void main(String[] args) {
        Game game;

        if (args.length == 3) {
            int x = Integer.parseInt(args[1]);
            int y = Integer.parseInt(args[2]);
            game = new Game(x, y);
        } else if (args.length == 2) {
            int x = Integer.parseInt(args[1]);
            game = new Game(x);
        } else {
            game = new Game();
        }

        try {
            game.run();
        } catch (InterruptedException e) {
        }
    }
}
