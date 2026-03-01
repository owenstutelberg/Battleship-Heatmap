package core;

import graphics.Renderer;
import ui.Screen;

public class GameLoop {
    private final Screen screen;
    private final Renderer renderer;

    public GameLoop(Screen screen, Renderer renderer) {
        this.screen = screen;
        this.renderer = renderer;
    }

    public void tick() {
        screen.update();
        renderer.repaint();
    }
}
