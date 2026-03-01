package core;

import javax.swing.Timer;

import graphics.GameWindow;
import graphics.Renderer;
import ui.Screen;

public class Game {
    private final GameLoop loop;

    public Game() {
        Screen screen = new Screen();
        Renderer renderer = new Renderer(screen);

        new GameWindow(renderer);

        loop = new GameLoop(screen, renderer);

        new Timer(16, e -> loop.tick()).start();
    }
}
